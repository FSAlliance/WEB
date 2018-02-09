package com.fsalliance.core.rest;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fsalliance.core.bo.CLS_BO_Login;
import com.fsalliance.core.vo.CLS_VO_User_I;

@Controller
@RequestMapping("/user")
public class CLS_REST_Login {
	
	@Resource(name = "boLogin")
	private CLS_BO_Login boLogin;
	
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
	
}
