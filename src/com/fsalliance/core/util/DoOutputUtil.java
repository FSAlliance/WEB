package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DoOutputUtil extends Thread {
		public InputStream is;           
		//构造方法    
		public DoOutputUtil(InputStream is) {            
			this.is = is;         
		}  
		@SuppressWarnings("unused")
		public void run() {  
			BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
			String str = null;  
			try {                  
				//这里并没有对流的内容进行处理，只是读了一遍 
				while ((str = br.readLine()) != null);             
			} catch (IOException e) {
				e.printStackTrace();             
			} finally { 
				if (br != null) { 
					try {  
						br.close();                      
					} catch (IOException e) { 
						e.printStackTrace();                
					}              
				}            
			}     
	}           	
}
