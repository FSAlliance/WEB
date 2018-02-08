package com.fsalliance.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import net.sf.json.JSONObject;

public class CLS_DataSource_Info {
	private String db_driver;
	private String db_url;
	private String db_username;
	private String db_password;
	private String db_testTable;
	private String db_dialect;
	private Properties db_properties;
	
	public Properties getDb_properties() {
		return db_properties;
	}

	public void setDb_properties(Properties dbProperties) {
		db_properties = dbProperties;
	}

	public String getDb_dialect() {
		return db_dialect;
	}

	public void setDb_dialect(String dbDialect) {		
		db_dialect = dbDialect;
	}

	public CLS_DataSource_Info () {		
		FileInputStream in;
		URL file = null;
		try {
			file = Thread.currentThread().getContextClassLoader().getResource("config.properties");
			String path = java.net.URLDecoder.decode(file.getPath(),"UTF-8");
			in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			String app_path;
			
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				app_path = prop.getProperty("app_path");
			} else {
				app_path = prop.getProperty("windows_app_path");
			}
			
			StringBuffer buffer = new StringBuffer();
			FileInputStream in2 = new FileInputStream(app_path + "cms_config.json");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in2));
	        String line = reader.readLine();       
	        while (line != null) {          
	            buffer.append(line);        
	            buffer.append("\n");        
	            line = reader.readLine();  
	        }
			
	        JSONObject jso = JSONObject.fromObject(buffer.toString());
	        db_driver = (String)jso.get("driver");
	        db_url = (String)jso.get("url");
	        db_username = (String)jso.get("username");
	        db_password = (String)jso.get("password");
	        db_testTable = (String)jso.get("testTable");
	        
	        db_properties = new Properties();
	        
	        if (db_driver.indexOf("oracle") != -1) {
	        	db_properties.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
	        } else if (db_driver.indexOf("mysql") != -1) {
	        	db_properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	        } else {
	        	
	        }
	        
	        in2.close();	        
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public String getDb_driver() {
		return db_driver;
	}

	public void setDb_driver(String db_driver) {
		this.db_driver = db_driver;
	}

	public String getDb_url() {
		return db_url;
	}

	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}

	public String getDb_username() {
		return db_username;
	}

	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}

	public String getDb_password() {
		return db_password;
	}

	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}

	public String getDb_testTable() {
		return db_testTable;
	}

	public void setDb_testTable(String db_testTable) {
		this.db_testTable = db_testTable;
	}
}
