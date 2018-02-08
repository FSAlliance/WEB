package com.fsalliance.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fsalliance.core.po.TabIp;

public class CLS_DAO_Ip extends HibernateDaoSupport {

	/**
	 *  IP多条件模糊查询DAO方法
	 *  
	 *  @param String ip ip地址 
	 *  @param String mac mac地址 
	 *  @param String user 使用者 
	 *  @return List<TabIp> list IP的list
	 *  @throws null  
	 */
	public List<TabIp> queryIp(String ip ,String mac,String user){
		List<TabIp> list =new ArrayList<TabIp>();
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT {IP.*} FROM tab_ip IP WHERE 1=1 ");
		if(ip != null ){
			sql.append(" AND IP.S_IP LIKE ? ");
			params.add("%" + ip + "%");
		}
		if(mac != null ){
			sql.append(" AND IP.S_MAC LIKE ? ");
			params.add("%" + mac + "%");
		}
		if(user != null ){
			sql.append(" AND IP.S_NAME LIKE ? ");
			params.add("%" + user + "%");
		}
		Session session =getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery =session.createSQLQuery(sql.toString());
		sqlQuery.addEntity("IP", TabIp.class);
		for(int i = 0; i < params.size(); i++){
			sqlQuery.setParameter(i, params.get(i));
		}
		List<Object> objects =sqlQuery.list();
		for (Object item : objects) {
			TabIp voIp = new TabIp(); 
			if(item != null){
				voIp = (TabIp) item;
			}
			list.add(voIp);
		}
		return list;
	}
}
