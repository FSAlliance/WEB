package com.fsalliance.core.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_FoldOrFile;
import com.fsalliance.core.bo.CLS_BO_FolderTree;
import com.fsalliance.core.bo.CLS_BO_Login;
import com.fsalliance.core.bo.CLS_BO_Upload;
import com.fsalliance.core.po.TabFile;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.CLS_FSAlliance_Types;
import com.fsalliance.core.vo.CLS_VO_File;
import com.fsalliance.core.vo.CLS_VO_FoldOrFile_I;
import com.fsalliance.core.vo.CLS_VO_Result;

@Controller
@RequestMapping("/downloadFile")
public class CLS_REST_Download {
	
	@Resource(name = "boFoldOrFile")
	private CLS_BO_FoldOrFile boFoldOrFile;
	/**
	 * 
	 * 方法名称：deleteFile
	 * 方法描述： 文件删除
	 * @param request
	 * @param response
	 * @param voFile
	 * @throws IOException
	 */
	@RequestMapping("/DeleteFile")
	public void deleteFile(HttpServletRequest request,HttpServletResponse response,CLS_VO_File vo) throws IOException{
		response.getWriter().print(JSONObject.fromObject(boFoldOrFile.deleteFile(vo)));

	}
	/**
	 * 
	 * 方法名称：openFile
	 * 方法描述： 文件查看
	 * @param request
	 * @param response
	 * @param voFile
	 * @return 
	 * @throws IOException
	 */
	@RequestMapping("/OpenFile")
	public void openFile(HttpServletRequest request,HttpServletResponse response,CLS_VO_File vo) throws IOException{

		//System.out.println("JSONObject.fromObject(result)"+JSONObject.fromObject(result));
		response.getWriter().print(JSONObject.fromObject(boFoldOrFile.openFile(vo)));
	}

	/**
	 * 
	 * 方法名称：downloadFile
	 * 方法描述： 下载文件到本地
	 * @param request		     请求
	 * @param response        响应
	 * @param voFileDowload   VO对象，下载路径 
	 * @throws IOException    异常
	 */
	@RequestMapping("/FileDownload")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,CLS_VO_File voFile )throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CLS_VO_Result result = new CLS_VO_Result();
		String Path = voFile.getPath();
		String FileName=voFile.getFileName();
		//配置路径
		File isFile = new File(Path);
		//检查文件是否存在
		if (!isFile.exists()) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			response.getWriter().print(JSONObject.fromObject(result));
			return;
		}
		//写流文件到前端浏览器
		ServletOutputStream out = response.getOutputStream();
	    //设置一个报头，用于提供一个推荐的文件名，并强制浏览器显示保存对话框
	    response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(FileName, "UTF-8"));

		BufferedInputStream   bis = null ;
		BufferedOutputStream  bos = null ;
		try {
			InputStream inputStream = new FileInputStream(Path); 
			bis = new BufferedInputStream(inputStream);//创建具有指定缓冲区大小的 BufferedInputStream 并保存其参数
			bos = new BufferedOutputStream(out);
			
			byte[] buff = new byte[2048];
			int bytesRead ;
			while(( bytesRead = bis.read(buff,0,buff.length) )!= -1){
				bos.write(buff,0,bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bis != null){
				bis.close();
			}
			if(bos != null){
				bos.close();
			}
		}
	}
}
