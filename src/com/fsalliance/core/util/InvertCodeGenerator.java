package com.fsalliance.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InvertCodeGenerator {

//	public static List<String> genCodes(int length,long num){
//	
//		List<String> results=new ArrayList<String>();
//	
//		for(int j=0;j<num;j++){
//		      String val = "";   
//		            
//		      Random random = new Random();   
//		      for(int i = 0; i < length; i++)   
//		      {   
//		        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字   
//		              
//		        if("char".equalsIgnoreCase(charOrNum)) // 字符串   
//		        {   
//		          int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母   
//		          val += (char) (choice + random.nextInt(26));   
//		        }   
//		        else if("num".equalsIgnoreCase(charOrNum)) // 数字   
//		        {   
//		          val += String.valueOf(random.nextInt(10));   
//		        }   
//		      }
//		      val=val.toLowerCase();
//		      if(results.contains(val)){
//		        continue;
//		      }else{
//		        results.add(val);
//		      }
//		    }
//		    return results;
//		           
//		    }  
	
	//生成随机数字和字母,  
    public static String getStringRandom(int length) {  

        String val = "";  
        Random random = new Random();        
        //length为几位密码 
        for(int i = 0; i < length; i++) {          
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }  

}