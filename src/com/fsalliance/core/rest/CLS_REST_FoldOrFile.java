package com.fsalliance.core.rest;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_FoldOrFile;
import com.fsalliance.core.util.DateJsonValueProcessor;
import com.fsalliance.core.vo.CLS_VO_FoldOrFile_I;

@Controller
@RequestMapping("/folder")
public class CLS_REST_FoldOrFile {

	@Resource(name = "boFoldOrFile")
	private CLS_BO_FoldOrFile boFoldOrFile;
	//添加文件夹
	@RequestMapping("/addFolder")
	public void addFolder(HttpServletRequest req, HttpServletResponse resp, CLS_VO_FoldOrFile_I vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boFoldOrFile.addFolder(vo)));
	}
	//根据文件夹路径查询一条文件夹数据
	@RequestMapping("/queryfileid")
	public void queryfileid(HttpServletRequest req, HttpServletResponse resp, String filepath) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boFoldOrFile.queryFileId(filepath)));
	}
	//多条件查询
	@RequestMapping("/queryfile")
	public void searchIp(HttpServletRequest req, HttpServletResponse resp,String fileName) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
    	resp.getWriter().print(JSONObject.fromObject(boFoldOrFile.queryFile(fileName),jsonConfig));
	}
}
