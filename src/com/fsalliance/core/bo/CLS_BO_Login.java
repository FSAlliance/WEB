package com.fsalliance.core.bo;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;


import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.po.TabUser;
import com.fsalliance.core.po.TabUserDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.InvertCodeGenerator;
import com.fsalliance.core.util.Tools;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Transactional
public class CLS_BO_Login {
	
	@Resource(name = "TabUserDAO")
	private TabUserDAO tabUserDAO;
	
	/**
	 * 登录
	 * @param user 用户信息
	 * @return
	 */
	public CLS_VO_Result login(CLS_VO_User_I user){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabUser> list = tabUserDAO.findBySPhoneNum(user.getPhoneNum());
		if (list.size() <= 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_USER_NOT_EXIST);
			return result;
		}
		TabUser tabUserR = null;
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			TabUser tabUser = list.get(i);
			if (tabUser.getSPassword().equals(user.getPassword())) {
				flag = true;
				tabUserR = tabUser;
				break;
			}
		}
		if (flag == true) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(tabUserR);
		}else{
			result.setRet(CLS_FSAlliance_Error.ERROR_PASSWORD);
		}
		return result;
	}
	//查询所用户
	public CLS_VO_Result queryUser(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_User_I> userlist = tabUserDAO.findAll();
		result.setContent(userlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	
	//注册新用户
	public CLS_VO_Result registerUser(CLS_VO_User_I user){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabUser> list = tabUserDAO.findBySPhoneNum(user.getPhoneNum());
		TabUser tabUser = new TabUser();
		if (list.size() <= 0) {
			String id = UUID.randomUUID().toString();
			tabUser.setSUserId(id);
			tabUser.setSPhoneNum(user.getPhoneNum());
			tabUser.setSPassword(user.getPassword());
			
			tabUser.setSName(user.getPhoneNum());
			String shareCode = InvertCodeGenerator.getStringRandom(6);
			List<TabUser> list_user = tabUserDAO.findBySInviteNum(user.getInviteNum());
			tabUser.setSInviteNum(shareCode);
			if (list_user.size() > 0) {
				tabUser.setSParentId(list_user.get(0).getSUserId());
			} else {
				tabUser.setSParentId("aaaaaaa");
			}
		
			 Timestamp ts = Timestamp.valueOf(Tools.translateTime(new Date()));  
			
			tabUser.setDtCreateTime(ts);
			tabUser.setDtLoginTime(ts);
			tabUserDAO.save(tabUser);
			//result.setRet(ret);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_USERID_EXIST);
			return result;
		}
		
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(tabUser);
		return result;

	}
	
	/**
	 * 更新用户的登录时间
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateLoginTime(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		TabUser tabUser = tabUserDAO.findById(user.getUserId());
		if (tabUser == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_USER_NOT_EXIST);
		} else {
			tabUser.setDtLoginTime(user.getLoginTime());
			TabUser mergeUser = tabUserDAO.merge(tabUser);
			if(mergeUser == null) {
				result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
			} else {
				result.setRet(CLS_FSAlliance_Error.ERROR_OK);
				result.setContent(mergeUser);
			}
		}
		return result;
	}

	

}
