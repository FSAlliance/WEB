package com.fsalliance.core.rest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.tool.hbm2x.hbm2hbmxml.elephant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_FoldOrFile;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.DateJsonValueProcessor;
import com.fsalliance.core.vo.CLS_VO_Computer;
import com.fsalliance.core.vo.CLS_VO_File;
import com.fsalliance.core.vo.CLS_VO_Result;

@Controller
@RequestMapping("/upload")
public class CLS_REST_Upload {

	@Resource(name = "boFoldOrFile")
	private CLS_BO_FoldOrFile boFoldOrFile;

	/**
	 * 上传（读取文件）
	 * 
	 * @param request
	 * @param response
	 * @param voFile
	 * @throws IOException
	 */
	@RequestMapping("/FileUpload")
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CLS_VO_Result result = new CLS_VO_Result();
		CLS_VO_File voFile = new CLS_VO_File();
		PrintWriter out = response.getWriter();
		String fileName = "";
		String fileType = "";
		// 文件上传解析之前 需要先判断 该表单是否为文件上传表单 enctype="multipart/form-data"
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {// 如果没有使用则返回.
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			out.print("<html><body><textarea>" + JSONObject.fromObject(result)
					+ "</textarea></body></html>");
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();// 创建FileItem对象的工厂
		ServletFileUpload upload = new ServletFileUpload(factory);// 创建一个文件上传处理器的实例upload
																	// ,用于处理文件上传
		List<FileItem> items = null;
		try {
			// 使用处理器对请求进行解析，它会把消息体中的每一块（部分）解析成一个FileItem对象。
			items = upload.parseRequest(request);// 处理文件上传
		} catch (FileUploadException e) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			out.print("<html><body><textarea>" + JSONObject.fromObject(result)
					+ "</textarea></body></html>");
			e.printStackTrace();
			return;
		}
		 // items为空，则没有文件需要上传
		if (items == null) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			out.print("<html><body><textarea>" + JSONObject.fromObject(result)
					+ "</textarea></body></html>");
			return;
		}
		String newPath = "";
		ArrayList<String> arrFileNames = new ArrayList<String>();
		Long size = null;
		String uploadPath = "";
		for (FileItem fileItem : items) {// 处理第一个FileItem
			// 判断服务器文件夹是否存在，如果不存则创建目录
			if (!fileItem.isFormField()) {// 如果文件项不是一个简单表单域，他就是一个上传文件
				size = fileItem.getSize();
				fileType = fileItem.getName().split("\\.")[fileItem.getName()
						.split("\\.").length - 1];
				fileName += fileItem.getName().substring(
						fileItem.getName().lastIndexOf("\\") + 1,
						fileItem.getName().lastIndexOf("."));
				System.out.println("fileName======="+fileName);
				newPath = uploadPath + File.separator + fileName + "."
						+ fileType;
				newPath = URLDecoder.decode(newPath,"UTF-8");
				File file1 = new File(uploadPath);
				//判断文件夹是否存在。
				if (!file1.exists()) {
					file1.mkdirs();
				}
				// 设置默认文件名
				File file = new File(newPath);
				// 文件存在禁止上传
				if (!file.exists()) {
					//文件存在可以上传
					try {
						fileItem.write(file);
						arrFileNames.add(fileName);
						voFile.setFileSize(size);
						voFile.setFileName(fileName);
						voFile.setFileType(fileType);
						result.setRet(CLS_FSAlliance_Error.ERROR_OK);
						result.setContent(voFile);
						response.setCharacterEncoding("UTF-8");
						response.setContentType("text/html;charset=UTF-8");
						out.print("<html><body><textarea>" + JSONObject.fromObject(result)
								+ "</textarea></body></html>");
					 	
					} catch (Exception e) {
						result.setRet(CLS_FSAlliance_Error.ERROR_DB_CONN);
						out.print("<html><body><textarea>"
								+ JSONObject.fromObject(result)
								+ "</textarea></body></html>");
						e.printStackTrace();
						return;
					}
					//文件存在不能上传
				}else {
					result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
					out.print("<html><body><textarea>"
							+ JSONObject.fromObject(result)
							+ "</textarea></body></html>");
				};
			} else {
				uploadPath += fileItem.getString("UTF-8");
			}
		}

	}
	/**
	 * 上传文件保存数据库
	 * 
	 * @param request
	 * @param response
	 * @param voFile
	 * @throws IOException
	 */
	@RequestMapping("/FileUploadSave")
	public void uploadFileSave(HttpServletRequest request,HttpServletResponse response, CLS_VO_File vo) throws Exception {
		CLS_VO_Result result = boFoldOrFile.addFileContent(vo);
		PrintWriter out = response.getWriter();
		out.print(JSONObject.fromObject(result));
		

	}

}
