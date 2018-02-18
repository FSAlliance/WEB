package com.fsalliance.core.bo;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fsalliance.core.po.TabUser;
import com.fsalliance.core.po.TabUserDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.InvertCodeGenerator;
import com.fsalliance.core.util.Tools;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Transactional
public class CLS_BO_User {
	
	@Resource(name = "TabUserDAO")
	private TabUserDAO tabUserDAO;

	//查询所用户
	public CLS_VO_Result queryUser(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_User_I> userlist = tabUserDAO.findAll();
		result.setContent(userlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}

	//获取用户信息
	public CLS_VO_Result getuserInfo(CLS_VO_User_I user){
		
		CLS_VO_Result result = new CLS_VO_Result();
		TabUser tabUser = tabUserDAO.findById(user.getUserId());
		
		if (tabUser == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent(tabUser);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(tabUser);
		}
		
		
		return result;

	}

	/**
	 * 更新支付宝帐号
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateAlipayNum(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateAlipayNum(user.getUserId(), user.getAlipayNum());
		
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}

	/**
	 * 更新用户名
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updateUserName(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updateUserName(user.getUserId(), user.getUserName());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}

	/**
	 * 更新密码
	 * @param user
	 * @return
	 */
	public CLS_VO_Result updatePassword(CLS_VO_User_I user) {
		CLS_VO_Result result = new CLS_VO_Result();
		int ret = tabUserDAO.updatePassword(user.getUserId(), user.getPassword());
		if(ret > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		} else {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_EXCEPTION);
		}
		return result;
	}
	

	
}
