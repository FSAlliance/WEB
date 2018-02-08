package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTools {

	public static void test(String strUrl) {
		BufferedReader bufferedReader = null;
		try {
			URL url = new URL(strUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			
        	bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            boolean firstLine = true; 
            String line = null; ; 
            while((line = bufferedReader.readLine()) != null){ 
                if(!firstLine){ 
                    sb.append(System.getProperty("line.separator")); 
                }else{ 
                    firstLine = false; 
                } 
                sb.append(line); 
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(bufferedReader != null){
                try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		}
	}
}
