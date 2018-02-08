package com.fsalliance.core.bo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.transaction.annotation.Transactional;

import com.fsalliance.core.dao.CLS_DAO_File;
import com.fsalliance.core.po.TabComputerDAO;
import com.fsalliance.core.po.TabFile;
import com.fsalliance.core.po.TabFileDAO;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.util.CLS_FSAlliance_Types;
import com.fsalliance.core.vo.CLS_VO_File;
import com.fsalliance.core.vo.CLS_VO_FoldOrFile_I;
import com.fsalliance.core.vo.CLS_VO_FolderTree;
import com.fsalliance.core.vo.CLS_VO_Result;
	/** 
	* <p>文件管理操作</p> 
	* 
	* @author 03865
	* @version 1.00 2015/07/13 03865 
	* @see TabComputerDAO 
	*/ 
@Transactional
public class CLS_BO_FoldOrFile {

	@Resource(name = "TabFileDAO")
	private TabFileDAO tabFileDAO;
	
	@Resource(name = "daoFile")
	private CLS_DAO_File daoFile;
	/**
	 *  添加文件夹
	 *  
	 *  @param CLS_VO_FoldOrFile_I vo 添加的文件基本信息
	 *  @return CLS_VO_Result result 添加的文件夹信息Content 和标志ret
	 *  @throws IOException 
	 *  @see CLS_VO_Result  返回值封装类
	 *  @see TabFileDAO 
	 */
	public CLS_VO_Result addFolder(CLS_VO_FoldOrFile_I vo) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		String filePath = vo.getFilePath() + File.separator
				+ vo.getFolderName();
		filePath = new String(filePath.getBytes("UTF-8"), "UTF-8");
		File mkdir = new File(filePath);
		//文件夹是否存在
		if (!mkdir.exists()) {
			mkdir.mkdirs();
			TabFile tabFile = new TabFile();
			String announcementId = UUID.randomUUID().toString();
			tabFile.setSId(announcementId);
			tabFile.setSAuthor(vo.getAuthor());
			tabFile.setITypeId(1L);
			tabFile.setSFileName(vo.getFolderName());
			tabFile.setSParentId(vo.getParentId());
			tabFile.setSFileType("文件夹");
			tabFile.setSDate(new Timestamp(new Date().getTime()));
			tabFile.setSFilePath(filePath);
			tabFileDAO.save(tabFile);
			CLS_VO_FoldOrFile_I voI = new CLS_VO_FoldOrFile_I();
			voI.setId(announcementId);
			voI.setFilePath(filePath);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(voI);
		}else{
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent("no");
		};
	return result;
	}

	/**
	 *  上传文件
	 *  
	 *  @param CLS_VO_FoldOrFile_I vo 添加的文件基本信息
	 *  @return CLS_VO_Result result 文件的基本信息
	 *  @throws IOException 
	 *  @see CLS_VO_Result  返回值封装类
	 *  @see TabFileDAO 
	 */
	public CLS_VO_Result addFileContent(CLS_VO_File vo) throws IOException {
		// 文件路径
		String filePath = vo.getPath() + File.separator + vo.getFileName()+"."+vo.getFileType();
		CLS_VO_Result result = new CLS_VO_Result();
		CLS_VO_FoldOrFile_I voI = new CLS_VO_FoldOrFile_I();
		TabFile tabFile = new TabFile();
		// 文件的ID（自动随机）
		String announcementId = UUID.randomUUID().toString();
		tabFile.setSId(announcementId);
		// 文件作者
		tabFile.setSAuthor(vo.getCurrentUser());
		// 文件标识符
		tabFile.setITypeId(2L);
		// 文件名字
		tabFile.setSFileName(vo.getFileName());
		// 文件父节点
		tabFile.setSParentId(vo.getParentId());
		// 文件类型（后缀）
		tabFile.setSFileType(vo.getFileType());
		// 文件大小
		tabFile.setSFileSize(vo.getFileSize());
		// 系统时间
		tabFile.setSDate(new Timestamp(new Date().getTime()));
		// 文件路径
		tabFile.setSFilePath(filePath);
		// 调用tabFileDAO的save(tabFile)方法。
		List list = tabFileDAO.findBySFileName(vo.getFileName());
		if (list.size() > 0) {
			result.setRet(CLS_FSAlliance_Error.ERROR_PARAM);
			result.setContent("no");
		}else {
			tabFileDAO.save(tabFile);
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent("ok");
		}
		return result;
	}

	/**
	 *  删除文件
	 *  
	 *  @param CLS_VO_File 
	 *  @return CLS_VO_Result result 
	 *  @throws null 
	 *  @see CLS_VO_Result  返回值封装类
	 *  @see TabFileDAO 
	 */
	public CLS_VO_Result deleteFile(CLS_VO_File vo){

		CLS_VO_Result result = new CLS_VO_Result();
		String path = vo.getPath();
		File file = new File(path);
		file.delete();
		String id = vo.getId();
		tabFileDAO.delete(id);
		CLS_VO_FoldOrFile_I voI = new CLS_VO_FoldOrFile_I();
		voI.setId(id);
		voI.setFilePath(vo.getPath());
		voI.setFolderName(vo.getFileName());
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(voI);
		return result;
	}
	/**
	 *  打开文件
	 *  
	 *  @param CLS_VO_File 
	 *  @return CLS_VO_Result result 
	 *  @throws IOException 
	 *  @see CLS_VO_Result  返回值封装类
	 *  @see TabFileDAO 
	 */
	public CLS_VO_Result openFile(CLS_VO_File vo) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		String Path = vo.getPath();
		String type = vo.getFileType();
		String res = null;
		File file = new File(Path);
		if ("doc".equals(type)) {
			try {
				FileInputStream fis = new FileInputStream(file);
				HWPFDocument doc = new HWPFDocument(fis);
				// 获取所有文本
				Range rang = doc.getRange();
				res = rang.text();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(res);
		} else if ("txt".equals(type)) {
			try {
				FileInputStream read = new FileInputStream(file);
				InputStreamReader fis = new InputStreamReader(read);
				BufferedReader bufferedReader = new BufferedReader(fis); 
				res  = bufferedReader.readLine();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result.setRet(CLS_FSAlliance_Error.ERROR_OK);
			result.setContent(res);
		}
		return result;
	}
	/**
	 *  文件模糊查询
	 *  
	 *  @param String filename 文件名
	 *  @return CLS_VO_Result
	 *  @throws null 
	 *  @see CLS_VO_Result 返回值封装类
	 */
	public CLS_VO_Result queryFile(String filename) throws IOException {
		CLS_VO_Result result = new CLS_VO_Result();
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		result.setContent(daoFile.queryFile(filename));
		return result;
	}
	/**
	 *  根据文件夹路径查询文件夹的id
	 *  
	 *  @param String sId 文件ID
	 *  @return 文件
	 *  @throws null 
	 *  @see CLS_VO_Result 
	 */
	public CLS_VO_Result queryFileId(String filepath){
		CLS_VO_Result result = new CLS_VO_Result();
		TabFile tabfile = new TabFile();
		List<TabFile> list = tabFileDAO.findBySFilePath(filepath);
		
		tabfile.setSId(list.get(0).getSId());
		tabfile.setITypeId(list.get(0).getITypeId());
		tabfile.setSAuthor(list.get(0).getSAuthor());
		tabfile.setSDate(list.get(0).getSDate());
		tabfile.setSFileName(list.get(0).getSFileName());
		tabfile.setSFilePath(list.get(0).getSFilePath());
		tabfile.setSFileSize(list.get(0).getSFileSize());
		tabfile.setSFileType(list.get(0).getSFileType());
		tabfile.setSParentId(list.get(0).getSParentId());		
		result.setContent(tabfile);
		result.setRet(CLS_FSAlliance_Error.ERROR_OK);
		return result;
	}
}
