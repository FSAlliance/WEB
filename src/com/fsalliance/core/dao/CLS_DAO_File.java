package com.fsalliance.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fsalliance.core.po.TabFile;


public class CLS_DAO_File extends HibernateDaoSupport {

	/**
	 *  文件模糊查询DAO方法
	 *  
	 *  @param String filename 文件名字 

	 *  @return List<TabFile> list 文件的list
	 *  @throws null  
	 */
	public List<TabFile> queryFile(String filename){
		List<TabFile> list =new ArrayList<TabFile>();
		List<Object> params=new ArrayList<Object>();
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT {FILE.*} FROM tab_file FILE WHERE 1=1 ");
		sql.append(" AND FILE.I_TYPE_ID = 2");
		if(filename != null ){
			sql.append(" AND FILE.S_FILE_NAME LIKE ? ");
			params.add("%" + filename + "%");
		}
		Session session =getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery sqlQuery =session.createSQLQuery(sql.toString());
		sqlQuery.addEntity("FILE", TabFile.class);
		for(int i = 0; i < params.size(); i++){
			sqlQuery.setParameter(i, params.get(i));
		}
		List<Object> objects =sqlQuery.list();
		for (Object item : objects) {
			TabFile voFile = new TabFile(); 
			if(item != null){
				voFile = (TabFile) item;
			}
			list.add(voFile);
		}
		return list;
	}
}
