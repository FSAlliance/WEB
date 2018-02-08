package com.fsalliance.core.bo;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.fsalliance.core.po.TabComputer;
import com.fsalliance.core.po.TabComputerDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Computer;
import com.fsalliance.core.vo.CLS_VO_Result;
	/** 
	* <p>虚拟机管理操作</p> 
	* 
	* @author 03865
	* @version 1.00 2015/07/13 03865 
	* @see TabComputerDAO 
	*/ 

public class CLS_BO_Computer {

	@Resource(name = "TabComputerDAO")
	private TabComputerDAO tabComputerDAO;
	
	/**
	 *  添加查询所有虚拟机拟机
	 *  
	 *  @return CLS_VO_Result 虚拟机所有数据
	 *  @throws null 
	 *  @see CLS_VO_Result 返回结果封装类
	 */
	public CLS_VO_Result queryComputer(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<CLS_VO_Result> computerlist = tabComputerDAO.findAll();
		result.setContent(computerlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	//查询所有未使用虚拟机
	public CLS_VO_Result queryComputer1(){
		CLS_VO_Result result = new CLS_VO_Result();
		List<TabComputer> computerlist = tabComputerDAO.findByIType(1L);
		result.setContent(computerlist);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
	/**
	 *  添加虚拟机
	 *  
	 *  @param CLS_VO_Computer vo 虚拟机信息的VO
	 *  
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回结果封装类
	 */
	public CLS_VO_Result addComputer(CLS_VO_Computer vo) {
		CLS_VO_Result result = new CLS_VO_Result();
		TabComputer tabComputer = new TabComputer();
		String id = UUID.randomUUID().toString();
		String user =vo.getUserName();
		String name =vo.getComputerName();
		String ip =vo.getComputerIp();
		Timestamp startTime =vo.getStartTime();
		Timestamp endTime =vo.getEndTime();
		long type =vo.getComputerType();
		//虚拟机id
		tabComputer.setSId(id);
		//ip
		tabComputer.setSIp(ip);
		//虚拟机名字
		tabComputer.setSName(name);
		//虚拟机使用者		
		tabComputer.setSUser(user);
		//虚拟机开始时间 
		tabComputer.setDtStarttime(startTime);
		//虚拟机 结束时间
		tabComputer.setDtEndtime(endTime);
		//虚拟机状态
		tabComputer.setIType(type);
		List<TabComputer> listcomputername= tabComputerDAO.findBySName(name);
		List<TabComputer> listcomputerip= tabComputerDAO.findBySIp(ip);
		//虚拟机名字存在
		if(listcomputername.size()>0){
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent("fale");
			return result;
		//虚拟机添加成功
		}else if (listcomputerip.size()>0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_DB_CONN);
			result.setContent("fale");
			return result;
		}else{
			tabComputerDAO.save(tabComputer);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent("ok");
			return result;
		}
	}
	/**
	 *  删除虚拟机
	 *  
	 *  @param CLS_VO_Computer vo 虚拟机信息的VO
	 *  
	 *  @return CLS_VO_Result 
	 *  @throws null 
	 *  @see CLS_VO_Result 返回结果封装类
	 */
	public CLS_VO_Result deleteComputer(CLS_VO_Computer vo) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		String id = vo.getId();
		String computername = vo.getComputerName();
		String ip = vo.getComputerIp();
		TabComputer persistentInstance=new TabComputer();
		persistentInstance.setSId(id);
		persistentInstance.setSName(computername);
		persistentInstance.setSIp(ip);
		tabComputerDAO.delete(persistentInstance);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent("ok");
		return result;
		}
	
	/**
	 *  修改虚拟机操作
	 *  
	 *  @param CLS_VO_Computer vo 虚拟机修改信息
	 *  
	 *  @return CLS_VO_Result 
	 *  @throws null 
	 *  @see CLS_VO_Result 返回结果封装类
	 */
	public CLS_VO_Result updateComputer(CLS_VO_Computer vo) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		//id
		String id = vo.getId();
		//虚拟机使用者
		String user =vo.getUserName();
		//虚拟机名字
		String name =vo.getComputerName();
		//ip地址
		String ip =vo.getComputerIp();
		//开始时间
		Timestamp startTime =vo.getStartTime();
		//结束时间
		Timestamp endTime =vo.getEndTime();
		//状态
		long type =vo.getComputerType();
		TabComputer tab= (TabComputer) tabComputerDAO.findById(id);
		//虚拟机名字是否有改动
		if(!name.equals(tab.getSName())){
			List<TabComputer> computerlist= tabComputerDAO.findBySName(name);	
			if(computerlist.size()>0){
				result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
				result.setContent(computerlist);
				return result;
				}
		//IP是否有改动
		}else if (!ip.equals(tab.getSIp())) {
			List<TabComputer> computerlistip= tabComputerDAO.findBySIp(ip);
			if(computerlistip.size()>0) {
				result.setRet(CLS_FSAlliance_Error.ERROR_DB_CONN);
				result.setContent(computerlistip);
				return result;
				}
		}
		tab.setSName(name);
		tab.setSIp(ip);
		tab.setSUser(user);
		tab.setDtStarttime(startTime);
		tab.setDtEndtime(endTime);
		tab.setIType(type);			
		tabComputerDAO.getHibernateTemplate().update(tab);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent("ok");
		return result;
      }
}
