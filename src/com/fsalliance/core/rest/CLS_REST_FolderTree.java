package com.fsalliance.core.rest;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_FolderTree;
import com.fsalliance.core.util.DateJsonValueProcessor;

@Controller
@RequestMapping("/tree")
public class CLS_REST_FolderTree {
	
	@Resource(name = "boFolderTree")
	private CLS_BO_FolderTree boFolderTree;
	
	@RequestMapping("/queryFolderTree")
	public void queryFolderTree(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boFolderTree.queryFolderTree()));
	}
	@RequestMapping("/queryFolderParent")
	public void queryFolderParent(HttpServletRequest req, HttpServletResponse resp, String id) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boFolderTree.queryFolderParent(id)));
	}
	
	@RequestMapping("/queryFolderGrid")
	public void queryFolderGrid(HttpServletRequest req, HttpServletResponse resp, String parentId)
		throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().print(JSONObject.fromObject(boFolderTree.queryFolderGrid(parentId),jsonConfig));
	}

}
