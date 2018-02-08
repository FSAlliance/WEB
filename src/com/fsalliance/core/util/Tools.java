package com.fsalliance.core.util;



import java.text.SimpleDateFormat;
import java.util.Date;


public class Tools {
	
	public static String translateTime(Date date) {
	
	    //设置要获取到什么样的时间
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    //获取String类型的时间
	    String createdate = sdf.format(date);
		return createdate;
	}
	
    
}
