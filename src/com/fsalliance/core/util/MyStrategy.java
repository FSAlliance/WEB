package com.fsalliance.core.util;

import java.io.FileOutputStream;

import org.hibernate.cfg.reveng.DelegatingReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;

public class MyStrategy extends DelegatingReverseEngineeringStrategy {

	
	public MyStrategy(ReverseEngineeringStrategy delegate) {
		super(delegate);
	}
	
	public String columnToHibernateTypeName(TableIdentifier table, String columnName, int sqlType, int length, int precision, int scale, boolean nullable, boolean generatedIdentifier) {		
		/*
		try {
		FileOutputStream fp = new FileOutputStream("d:\\b.log", true);
		fp.write(new String("column " + columnName + " " + sqlType + " " + length + " " + precision + " " + scale + "\n").getBytes());
		} catch (Exception E) {
			
		}*/
		
		if (sqlType == 1111 && columnName.indexOf("DT_") == 0) {
			return "java.sql.Timestamp";
		} else if (sqlType == 1111 && columnName.indexOf("F_") == 0) {
			return "java.lang.Double";
		}  else if (sqlType == 1111 && columnName.indexOf("I_") == 0) {
			return "java.lang.Double";
		} else if (sqlType == 3 && columnName.indexOf("I_") == 0) {
			return "java.lang.Long";
		} else if (sqlType == 3) {
			return "java.lang.Long";
		} else if (sqlType == 1111) {
			return "java.sql.Timestamp";
		} else if (sqlType == 12 && length == 4000) {
			return "text";
		} else {
			return super.columnToHibernateTypeName(table, columnName, sqlType, length, precision, scale, nullable, generatedIdentifier);
		}		
	}
}
