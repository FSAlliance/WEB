package com.fsalliance.core.util;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class ConfigManager {

//	private static final String PFILE = "/root/apache-tomcat-6.0.24/webapps/fshare/WEB-INF/classes";
	private static final String PFILE = "root\\apache-tomcat-6.0.24\\webapps\\fshare\\WEB-INF\\classes\\config.properties";
	private File m_file = null;

	private long m_lastModifiedTime = 0;

	private Properties m_props = null;

	private static ConfigManager m_instance = new ConfigManager();

	private ConfigManager() {
		m_file = new File(PFILE);
		m_lastModifiedTime = m_file.lastModified();
		if (m_lastModifiedTime == 0) {
			System.err.println(PFILE + " file does not exist!");
		}
		m_props = new Properties();
		try {
			m_props.load(new FileInputStream(PFILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public static ConfigManager getInstance() {
		return m_instance;
	}

	final public String getConfigItem(String name, String defaultVal) {
		long newTime = m_file.lastModified();

		if (newTime == 0) {

			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
			return defaultVal;
		} else if (newTime > m_lastModifiedTime) {
			// Get rid of the old properties
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;
		String val = m_props.getProperty(name);
		if (val == null) {
			return defaultVal;
		} else {
			return val;
		}
	}

	final public boolean setConfigItem(String name, String value) {
		boolean flag = false;
		long newTime = m_file.lastModified();

		if (newTime == 0) {

			if (m_lastModifiedTime == 0) {
				System.err.println(PFILE + " file does not exist!");
			} else {
				System.err.println(PFILE + " file was deleted!!");
			}
		} else if (newTime > m_lastModifiedTime) {
			// Get rid of the old properties
			m_props.clear();
			try {
				m_props.load(new FileInputStream(PFILE));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		m_lastModifiedTime = newTime;
		m_props.setProperty(name, value);
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(PFILE);
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			m_props.store(fos, "edit language");
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

}