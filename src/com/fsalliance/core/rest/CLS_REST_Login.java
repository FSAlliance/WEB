package com.fsalliance.core.rest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsalliance.core.bo.CLS_BO_Login;
import com.fsalliance.core.bo.CLS_BO_User;
import com.fsalliance.core.util.CLS_FSAlliance_Error;
import com.fsalliance.core.vo.CLS_VO_Result;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Controller
@RequestMapping("/user")
public class CLS_REST_Login {
	
	@Resource(name = "boLogin")
	private CLS_BO_Login boLogin;
	
//	@Resource(name = "boUser")
//	private CLS_BO_User boUser;
	
	@RequestMapping("/checkSign")
	public void login(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.login(user)));
	}
	@RequestMapping("/queryuser")
	public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.queryUser()));
	}
	
	
	@RequestMapping("/register")
	public void register(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.registerUser(user)));
	}
	@RequestMapping("/getuserinfo")
	public void getUserInfo(HttpServletRequest req, HttpServletResponse resp,CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.getuserInfo(user)));
	}
	@RequestMapping("/updateLoginTime")
	public void updateLoginTime(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateLoginTime(user)));
	}
//	
	@RequestMapping("/updateAlipayNum")
	public void updateAlipayNum(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateAlipayNum(user)));
	}
	
	@RequestMapping("/updateUserName")
	public void updateUserName(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateUserName(user)));
	}

	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updatePassword(user)));
	}
	
	@RequestMapping("/updateUserPhoto")
	public void updateUserPhoto(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateUserPhoto(req, resp))); 
	
	}
	@RequestMapping("/updateUserHeadInfo")
	public void updateUserHeadInfo(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateUserHeadInfo(user))); 
	}
}
