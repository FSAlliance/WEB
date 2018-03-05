package com.fsalliance.core.rest;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fsalliance.core.bo.CLS_BO_Login;
import com.fsalliance.core.bo.CLS_BO_User;
import com.fsalliance.core.util.DateJsonValueProcessor;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Controller
@RequestMapping("/user")
public class CLS_REST_Login {
	
	@Resource(name = "boLogin")
	private CLS_BO_Login boLogin;
	
	@Resource(name = "boUser")
	private CLS_BO_User boUser;
	
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
		resp.getWriter().print(JSONObject.fromObject(boUser.getuserInfo(user)));
	}
	@RequestMapping("/updateLoginTime")
	public void updateLoginTime(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boLogin.updateLoginTime(user)));
	}
	
	@RequestMapping("/updateAlipayNum")
	public void updateAlipayNum(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updateAlipayNum(user)));
	}
	
	@RequestMapping("/updateUserName")
	public void updateUserName(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updateUserName(user)));
	}

	@RequestMapping("/updatePassword")
	public void updatePassword(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updatePassword(user)));
	}
	
	@RequestMapping("/updateUserPhoto")
	public void updateUserPhoto(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updateUserPhoto(req, resp))); 
	}
	
	@RequestMapping("/updateUserHeadInfo")
	public void updateUserHeadInfo(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_I user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updateUserHeadInfo(user))); 
	}
	
	@RequestMapping("/getPresentRecordList")
	public void getPresentRecordList(HttpServletRequest req, HttpServletResponse resp, @RequestParam("userId") String userId, int pageNo, int pageSize) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().print(JSONObject.fromObject(boUser.getCashRecordList(userId, pageNo, pageSize),jsonConfig)); 
	}
	
	@RequestMapping("/getIncomeRecordList")
	public void getIncomeRecordList(HttpServletRequest req, HttpServletResponse resp, @RequestParam("userId") String userId, int pageNo, int pageSize) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().print(JSONObject.fromObject(boUser.getIncomeRecordList(userId, pageNo, pageSize),jsonConfig)); 
	}
	
	@RequestMapping("/persent")
	public void persent(HttpServletRequest req, HttpServletResponse resp, @RequestParam("userId") String userId, Double money) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUser.updateBalance(userId, money))); 
	}
}
