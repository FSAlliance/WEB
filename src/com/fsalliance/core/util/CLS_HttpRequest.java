package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CLS_HttpRequest {
	
	private static final Logger log = LoggerFactory.getLogger(CreateJSONFile.class);

	
	/**
	 * 方法名称：sendzip
	 * 方法描述：zip文件上传
	 * 参          数：filePath，params
	 * 返  回  值 ：String
	 * 作者日期：chenanhai   2014年7月30日17:14:00
	 * 创  建  人 ：chenanhai   2014年7月30日17:14:00
	 * 其          他：
	 */
	
	public static String sendzip(String filePath, String params) {
		
		String upLoadURL = CLS_FSAlliance_ConfigTool.getValue("UpLoadURL");
		if(params!=null && params!= ""){
			upLoadURL =upLoadURL+"?param="+params;
		}
		StringBuffer temp = null;
		//filePath="D:/Tomcat 6.0/Data.zip";
		try {
			URL  url = new URL(upLoadURL);
			//URL url = new URL("http://10.30.11.138:8080/HNUploader/rest/DataExtraction?Version=1.0&SecurityInfo={'Code':'1111','TimeStamp':'343545'}&InterfaceName=getOrganizationInfo") ;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setChunkedStreamingMode(1024*1024); 
			conn.setRequestMethod("POST");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			String fname = filePath;
			File file = new File(fname);
			conn.setRequestProperty("Content-Type","multipart/form-data;file="+file.getName());
			conn.setRequestProperty("filename",file.getName());
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(),"UTF-8"));
			String line = "";
			temp = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				 java.net.URLDecoder.decode(line, "UTF-8");
				 temp.append(line);
				log.info(line);
			}
			reader.close();
		} catch (Exception e) {
			log.error("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
		return temp.toString();

	}
}
