package com.fsalliance.core.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.dao.CLS_DAO_Ip;
import com.fsalliance.core.po.TabComputerDAO;
import com.fsalliance.core.po.TabIp;
import com.fsalliance.core.po.TabIpDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Ip;
import com.fsalliance.core.vo.CLS_VO_Result;
	/** 
	* <p>IP管理操作</p> 
	* 
	* @author 03865
	* @version 1.00 2015/07/13 03865 
	* @see TabComputerDAO 
	*/ 

@Transactional
public class CLS_BO_Ip {

	@Resource(name = "TabIpDAO")
	private TabIpDAO tabIpDAO;

	@Resource(name = "daoIp")
	private CLS_DAO_Ip daoIp;
	/**
	 *  查询所有IP地址 
	 *  
	 *  @return CLS_VO_Result ip所有数据
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result queryIp(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_Result> iplist = tabIpDAO.findAll();
		result.setContent(iplist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	/**
	 *  添加Ip地址
	 *  
	 *  @param String ipStart  ip区间开始
	 *  @param String ipEnd    ip区间结束 
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result addIp(String ipStart, String ipEnd) {
		CLS_VO_Result result = new CLS_VO_Result();
		//ip前半部分              例子 ：10.30.11. 
		String ipStartBeginning = ipStart.substring(0,ipStart.lastIndexOf("."))+".";
		//ip开始的后尾数  例子 ：002
		int ipStartFinish = Integer.parseInt(ipStart.substring(ipStart.lastIndexOf(".")+1,ipStart.length()));
		//IP结束的后尾数    例子 ：224
		int ipEndFinish = Integer.parseInt(ipEnd.substring(ipEnd.lastIndexOf(".")+1,ipEnd.length()));
		ArrayList<TabIp> list = new ArrayList<TabIp>();
		for(int i=ipStartFinish; i <= ipEndFinish;i++){
			//自动补齐三位数字 
			String  str = String.format("%03d", i);
			//生成的ip地址 
			String ipName= ipStartBeginning +str;
			//自动生成的ID	
			String id = UUID.randomUUID().toString();
			//状态初始化 
			long type =1;
			TabIp tabIp = new TabIp();
			tabIp.setSId(id);
			tabIp.setSIp(ipName);
			tabIp.setIType(type);
			tabIp.setSMac("未分配");
			tabIp.setSName("未分配");
			List<TabIp> listIpName = tabIpDAO.findBySIp(ipName);
			//IP地址存在
			if(listIpName.size()>0){
				continue;
			}else{
				tabIpDAO.save(tabIp);
				list.add(tabIp);
			}
		}
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(list);
		return result;
				
	}
	/**
	 *  删除IP地址
	 *  
	 *  @param CLS_VO_Ip vo 
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result deleteIp(CLS_VO_Ip vo) {
		CLS_VO_Result result = new CLS_VO_Result();
		String id = vo.getId();
		String ipName = vo.getIpName();
		String macName = vo.getMacName();
		TabIp tabIp=new TabIp();
		tabIp.setSId(id);
		tabIp.setSIp(ipName);
		tabIp.setSMac(macName);
		tabIpDAO.delete(tabIp);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent("ok");
		return result;
		}
	/**
	 *  分配IP地址
	 *  
	 *  @param CLS_VO_Ip vo 分配的MAC地址和用户 
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result updateIp(CLS_VO_Ip vo) {
		CLS_VO_Result result = new CLS_VO_Result();
		//id
		String id = vo.getId();
		//ip地址
		String ip =vo.getUserName();
		//使用者
		String userName =vo.getUserName();
		//mac地址
		String macName =vo.getMacName();
		long type;
		//状态自动改变
		if((userName != "" || macName != "")){
		  type =2;
		   }else{
	      type =1;
		};
		TabIp tab= tabIpDAO.findById(id);
		if ("".equals(userName) && "".equals(macName)) {
		 userName = "未分配 ";
		 macName = "未分配";
		}else{
			if (("".equals(userName) || "未分配 ".equals(userName)) && macName != "" && macName != "未分配 " ) {
				userName = "未填写";
			};
			if (("".equals(macName) || "未分配 ".equals(macName)) && userName != "" && userName != "未分配 ") {
				macName = "未填写";
			};
		};
		tab.setSName(userName);
		tab.setIType(type);
		tab.setSMac(macName);
		tabIpDAO.getHibernateTemplate().update(tab);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent("ok");
		return result;
      }
	/**
	 *  IP多条件模糊查询
	 *  
	 *  @param String ip ip地址 
	 *  @param String mac mac地址 
	 *  @param String user 使用者 
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result queryIp(String ip,String mac,String user) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(daoIp.queryIp(ip, mac, user));
		return result;
	}
}
