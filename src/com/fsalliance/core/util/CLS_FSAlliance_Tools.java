package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hwpf.extractor.WordExtractor;

public class CLS_FSAlliance_Tools {

	public static String md5s(String plainText) {
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str.toUpperCase();
	}
	
	/**
	 * 方法名称：isCode
	 * 方法描述：判断code码是否一致
	 * 参          数：String name  String reqTimeStamp  String reqCode
	 * 返  回  值 ：boolean
	 * 作者日期：jiaoshiyu    2014-07-24 上午11:08:40
	 * 创  建  人 ：jiaoshiyu    2014-07-24 上午11:08:40
	 * 其          他：
	 */
	public static boolean isCode(String name, String reqTimeStamp, String reqCode){
		String code = md5s(name + reqTimeStamp);
		if (code.equals(reqCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 方法名称：changeType
	 * 方法描述：双方对接平台案件类型相互转换
	 * 参          数：flag 外部平台为0 科法平台为1
	 * 参          数：type 类型值
	 * 返  回  值 ：boolean
	 * 作者日期：jiaoshiyu    2014-07-24 上午11:08:40
	 * 创  建  人 ：jiaoshiyu    2014-07-24 上午11:08:40
	 * 其          他：
	 */
	public static int changeType(int flag, int type) {
		int retType = 0;
		if (flag == 0) {    //外部平台类型转换为科法平台数据库中的相应类型
			
			if (type == 1) {  //刑事
				retType = 2;
			} else if (type == 2) {  //民事
				retType = 1;
			} else if (type == 3) {  //经济纠纷 ----科法平台没有此类型
				retType = 8;
			} else if (type == 4) {  //海事海商  ----科法平台没有此类型
				retType = 11;
			} else if (type == 5) {  //知识产权  ----科法平台没有此类型
				retType = 12;
			} else if (type == 6) {  //行政
				retType = 3;
			} else if (type == 7) {  //赔偿
				retType = 6;
			} else if (type == 8) {  //执行
				retType = 4;
			} else if (type == 9) {  //交通刑事  ----科法平台没有此类型
				retType = 9;
			} else if (type == 10) {  //交通民事  ----科法平台没有此类型
				retType = 10;
			} else if (type == 255) {  //其他
				retType = 5;
			}
		}
		if (flag == 1) {    //科法平台数据库中的相应类型转换为外部平台类型
			
			if (type == 1) {  //民事
				retType = 2;
			} else if (type == 2) {  //刑事
				retType = 1;
			} else if (type == 3) {  //行政
				retType = 6;
			} else if (type == 4) {  //执行
				retType = 8;
			} else if (type == 5) {  //其他
				retType = 255;
			} else if (type == 6) {  //赔偿
				retType = 7;
			} else if (type == 7) {  //申请再审
				retType = 255;
			} else if (type == 8) {  //经济纠纷 ----科法平台没有此类型
				retType = 3;
			} else if (type == 9) {  //交通刑事  ----科法平台没有此类型
				retType = 9;
			} else if (type == 10) {  //交通民事  ----科法平台没有此类型
				retType = 10;
			} else if (type == 11) {  //海事海商  ----科法平台没有此类型
				retType = 4;
			} else if (type == 12) {  //知识产权  ----科法平台没有此类型
				retType = 5;
			}
		}
		return retType;
	}
	
	/**
	 * 方法名称：changeType
	 * 方法描述：将科法画面类型转换为外部画面类型
	 * 参          数：type 类型值
	 * 返  回  值 ：String
	 * 作者日期：jiaoshiyu    2014-07-24 上午11:08:40
	 * 创  建  人 ：jiaoshiyu    2014-07-24 上午11:08:40
	 * 其          他：
	 */
	public static String changeScreenType(String type) {
		String screenType = "0";
		if (type.equals("1")) {  //全景
			screenType = "2";
		} else if (type.equals("4")) {  //合成
			screenType = "1";
		} else if (type.equals("11")) {   //法官席
			screenType = "3";
		} else if (type.equals("13")) {   //公诉席
			screenType = "4";
		} else if (type.equals("9")) {   //辩护席
			screenType = "5";
		} else if (type.equals("18")) {   //旁听席
			screenType = "6";
		}
		return screenType;
	}
	
	/**
	 * 方法名称：getDocument
	 * 方法描述：读取word
	 * 参          数：InputStream
	 * 返  回  值 ：String
	 * 作者日期：jiaoshiyu    2014-07-24 上午11:08:40
	 * 创  建  人 ：jiaoshiyu    2014-07-24 上午11:08:40
	 * 其          他：
	 */
	public static String getDocument(InputStream is) {   
		try {
			 WordExtractor we = new WordExtractor(is);
			 StringBuffer buffer = new StringBuffer();
			 //System.out.println("dddd"+we.getText());
			 //System.out.println("aaaa"+we.getTextFromPieces());
			 for (int i = 0; i < we.getParagraphText().length; i++) {
				 buffer.append(we.getParagraphText()[i]);
				 //System.out.println("xxxx"+i+":"+we.getParagraphText()[i]);
			 }
			 return buffer.toString();
		} catch (IOException e) {
		 // TODO 自动生成 catch 块
		 e.printStackTrace();
		 return null;
		}
	}
	/**
	 * 方法名称：getDocument
	 * 方法描述：时间转字符
	 * 参          数：InputStream
	 * 返  回  值 ：String
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	public static String formatData(Date date){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");                
		 String formatDATE= sdf.format(date);
		return formatDATE;
	}
	
		
	/**
	 * 方法名称：getTrialRecord
	 * 方法描述：下载笔录文件
	 * 参          数：strUrl,strPath
	 * 返  回  值 ：String
	 * 作者日期：jiaoshiyu    2014-07-24 上午11:08:40
	 * 创  建  人 ：jiaoshiyu    2014-07-24 上午11:08:40
	 * 其          他：
	 */
	public static String getTrialRecord(String strUrl,String strPath){
		String result = null;
		try {
			
			URL url;
			url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			DataInputStream input = new DataInputStream(conn.getInputStream()); 
			DataOutputStream output = new DataOutputStream(new FileOutputStream(strPath)); 
			byte[] buffer = new byte[1024 * 8]; 
			int count = 0; 
			while ((count = input.read(buffer)) > 0) { 
				output.write(buffer, 8, count); 
			} 
			output.close(); 
			input.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	/**
	 * 方法名称：GetWebserviceData
	 * 方法描述：获取远程客户端数据
	 * 参          数：@param strUrl
	 * 参          数：@param requestType
	 * 参          数：@return
	 * 返  回  值：String
	 * 作者日期: jiaoshiyu    2014-07-31 上午11:08:40
	 * 创  建  人： jiaoshiyu    2014-07-31 上午11:08:40
	 * 其        他：
	 */
	public static String GetWebserviceData(String strUrl ,String requestType) {
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod(requestType);//多数采用get
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setConnectTimeout(5000);

			InputStream in = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in, "UTF-8"));
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();

			while (line != null) {
				line = java.net.URLDecoder.decode(line, "UTF-8");
				// temp.append(line).append("\r\n");
				temp.append(line);
				System.out.println(line);
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
			return temp.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "[]";
	}
	
	/***
	 * 获得当前项目路径
	 * @return
	 */
	public static String projectPath(){
		String pathWIN =System.getProperty("user.dir");
		//获得WIN回退一层的目录
		String pathWINBack = pathWIN.substring(0,pathWIN.lastIndexOf("\\"));
		String pathJAVA =pathWINBack.replace("\\", "/");
		return pathJAVA+"/note";
		
	}

}
