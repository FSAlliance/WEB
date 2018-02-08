package com.fsalliance.core.util;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;


public class CLS_FSAlliance_Types {
	//SYSTEM MAIL SEND INTERVAL
	
	public static String web_name = null;
	public static String app_path = null;
	public static String db_driver = null;
	public static String db_url = null;
	public static String db_username = null;
	public static String db_password = null;
	public static String db_testTable = null;
	public static String local_dev_id = null;
	public static String local_dev_ip = null;
	public static int local_dev_port = 7000;
	
	//ADD CHL 20140306对接使用
    public static final int DATA_RATE_TYPE_PRIMARY = 1; 		//主码流
    public static final int DATA_RATE_TYPE_SECONDARY = 2; 		//子码流
    public static final int EQUIPMENT_MANUFACTURERS = 20; 		//天地伟业
    public static final int IS_FIRST_CHOICE = 1; 				//是否首选
    public static final String RESOLUTION_TYPE = "1024x768"; 	//分辨率
    public static final String STREAMING_MEDIA_SERVER = "1"; 	//流媒体服务器
    public static final String VIDEO_HOST = "2"; 				//编码器
	
	//DOG TYPE
	
	public static final int DOG_TYPE_VS	      =  2105956963;   // 流媒体服务器  
	public static final int DOG_TYPE_REC	  =  306706400;	   // 录像存储服务器
	public static final int DOG_TYPE_TV       =  1372284335;   // 电视墙服务器
	public static final int DOG_TYPE_CMS200   =  747080738;    // CMS200路
	public static final int DOG_TYPE_CMS500   =  1723606147;   // CMS500路
	public static final int DOG_TYPE_CMS1000  =  31218185;     // CMS1000路 
	public static final int DOG_TYPE_CMS2000  =  2065565469;   //CMS2000路
	public static final int DOG_TYPE_CMS5000  =  1047129100;   //CMS5000路
	public static final int DOG_TYPE_CMS10000 =  325254036;    // CMS10000路
	public static final int DOG_TYPE_CMS_MAX  =  977616876;    // CMS旗舰版	
	public static final int DOG_TYPE_IAS200   = 200;           //视频诊断200点狗
	public static final int DOG_TYPE_IAS500   = 500;           //视频诊断500点狗
	public static final int DOG_TYPE_IAS1000  = 1000;          //视频诊断1000点狗
	
	public static final long FILE_TYPE_FOLAD2    = 2l;    //文件
	public static final long FILE_TYPE_FOLAD1    = 1l;    //文件夹
	
	public static final String SELF_ARCHIVE_FLAG = "@001@";  //石家庄中院第三方对接，天地案件标识
	
	public static ThreadLocal<String> current_user_id = new ThreadLocal<String>() {
		public String initialValue() {
			return "";
		}
	};
	
	static   
	{    		
		FileInputStream in;
		URL file = null;
		try {
			file = Thread.currentThread().getContextClassLoader().getResource("config.properties");
			String path = java.net.URLDecoder.decode(file.getPath(),"UTF-8");
			in = new FileInputStream(path);
			Properties prop = new Properties();
			prop.load(in);
			in.close();
			
			if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") == -1) {
				app_path = prop.getProperty("app_path");
				if (app_path == null){
					app_path = "/root/x1/";
				}
				//logger.info("use clientsdk ms");
			} else {
				app_path = prop.getProperty("windows_app_path");
			}
			web_name = prop.getProperty("web_name");
			local_dev_ip = prop.getProperty("client_ip");
	       
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}