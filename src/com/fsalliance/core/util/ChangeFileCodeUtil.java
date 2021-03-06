package com.fsalliance.core.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 改变文件编码格式，用于解决txt乱码
 * @author gxp
 *
 * 2014-8-12 上午08:33:25
 *
 */
public class ChangeFileCodeUtil {
	// 读取的文件  
    private String fileIn;  
  
    // 读取時文件用的编码  
    private String fileInEn;  
  
    // 写出的文件  
    private String fileOut;  
  
    // 写出時文件用的编码  
    private String fileOutEn;  
  
    /**
     * 进行文件编码格式转换
     * @param filePath
     */
    public static void change(String filePath){
		ChangeFileCodeUtil changeFileCode = new ChangeFileCodeUtil();
		String path = filePath;
		File txtFile = new File(path);
		String fileCode = changeFileCode
				.getFileEnCode(path);

		if (fileCode != null && !"".equals(fileCode)) {
			changeFileCode.setFileIn(txtFile.getPath(),
					fileCode);// 如果文件编码为ANSI用GBK来读，如果是UTF-8用UTF-8来读
			changeFileCode.setFileOut(
					txtFile.getPath(), "UTF-8");// UTF-8则文件编码为UTF-8，
												// 如果为GBK，编码为ANSI
			changeFileCode.start();
		}
	}
    
    /**
     * 获取源文件的编码
     * @param filePath 源文件所在的绝对路径
     * @return
     */
    private String getFileEnCode(String filePath) {
		InputStream inputStream = null;
		String code = ""; 
		try {
			inputStream = new FileInputStream(filePath);  
	        byte[] head = new byte[3];  
	        inputStream.read(head);   
	         
	   
	            code = "gbk";  
	        if (head[0] == -1 && head[1] == -2 )  
	            code = "UTF-16";  
	        if (head[0] == -2 && head[1] == -1 )  
	            code = "Unicode";  
	        if(head[0]==-17 && head[1]==-69 && head[2] ==-65)  
	            code = "UTF-8";  
	          
	        System.out.println(code);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return code;
	}
  
    private void setFileIn(String fileInPath, String fileInEncoding) {  
        this.setFileIn(fileInPath);  
        this.setFileInEn(fileInEncoding);  
    }  
  
    private void setFileOut(String fileOutPath, String fileOutEncoding) {  
        this.setFileOut(fileOutPath);  
        this.setFileOutEn(fileOutEncoding);  
    }  
      
    private void start() {     
        String str = this.read(fileIn,fileInEn);     
        this.write(fileOut, fileOutEn, str);     
    }     
      
    /** 
     * 读文件 
     *  
     * @param fileName 
     * @param encoding 
     */  
    private String read(String fileName, String encoding) {  
        try {  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(fileName), encoding));  
  
            String string = "";  
            String str = "";  
            while ((str = in.readLine()) != null) {  
                string += str + "\r\n";
            }  
            in.close();  
            return string;  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return "";  
    }  
  
    /** 
     * 写文件 
     *  
     * @param fileName 
     *            新的文件名 
     * @param encoding 
     *            写出的文件的编码方式 
     * @param str 
     */  
    private void write(String fileName, String encoding, String str) {  
        try {  
            Writer out = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(fileName), encoding));  
            out.write(str);  
            out.close();
            System.out.println("***编码转换成功！");
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }
    
    public String getFileIn() {  
        return fileIn;  
    }  
  
    public void setFileIn(String fileIn) {  
        this.fileIn = fileIn;  
    }  
  
    public String getFileInEn() {  
        return fileInEn;  
    }  
  
    public void setFileInEn(String fileInEn) {  
        this.fileInEn = fileInEn;  
    }  
  
    public String getFileOut() {  
        return fileOut;  
    }  
  
    public void setFileOut(String fileOut) {  
        this.fileOut = fileOut;  
    }  
  
    public String getFileOutEn() {  
        return fileOutEn;  
    }  
  
    public void setFileOutEn(String fileOutEn) {  
        this.fileOutEn = fileOutEn;  
    }  
  
    /**
     * 测试例子
     * @param args
     */
    public static void main(String[] args) { 
    	String InputFilePath = "c:\\";
    	String FileName = "11.txt";
    	
    	ChangeFileCodeUtil changeFileCode = new ChangeFileCodeUtil();  
        String path = InputFilePath+FileName;  
        File file = new File(path);  
        String fileCode = changeFileCode.getFileEnCode(path); 
        
        if(fileCode!=null && !"".equals(fileCode)) {
	        changeFileCode.setFileIn(file.getPath(), fileCode);//如果文件编码为ANSI用GBK来读，如果是UTF-8用UTF-8来读  
	        changeFileCode.setFileOut(file.getPath(), "UTF-8");//UTF-8则文件编码为UTF-8， 如果为GBK，编码为ANSI  
	        changeFileCode.start();  
        }
    } 
}  
