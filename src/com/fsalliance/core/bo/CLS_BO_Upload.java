package com.fsalliance.core.bo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Result;

public class CLS_BO_Upload {
	
	private static final Log log = LogFactory.getLog(CLS_BO_Upload.class);
	
	public CLS_VO_Result uploadFile(HttpServletRequest request, HttpServletResponse response,
			String srcPath,String fileName) throws Exception{
		
		request.setCharacterEncoding("UTF-8");
		CLS_VO_Result result = new CLS_VO_Result();
		if(srcPath == null || srcPath.equals("")){
			log.error("srcPath == null || srcPath.equals(\"\")");
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			return result;
		}
		srcPath.replace("\\", "\\\\");
		File file = new File(srcPath);
		System.out.println("srcPath----2----" + srcPath);
		FileInputStream inStream = new FileInputStream(file);
		BufferedInputStream buff=new BufferedInputStream(inStream);
		File outFile = new File(File.separator + "share" + File.separator + "shareFile" 
				+ File.separator + fileName);
		FileOutputStream outStream = new FileOutputStream(outFile);
		BufferedOutputStream outBuf = new BufferedOutputStream(outStream);
		byte [] b=new byte[1024];
		long k=0;
		while(buff.read() > 0){
			int j = buff.read(b,0,1024);
			k = k + j;
			outBuf.write(b, 0, j);
		}
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
}
