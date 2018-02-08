package com.fsalliance.core.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_Ip;
import com.fsalliance.core.dao.CLS_DAO_Ip;
import com.fsalliance.core.vo.CLS_VO_Ip;

@Controller
@RequestMapping("/ip")
public class CLS_REST_Ip {

	@Resource(name = "boIp")
	private CLS_BO_Ip boIp;
	
	//查询
	@RequestMapping("/queryIp")
	public void queryIp(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boIp.queryIp()));
	}
	//删除
	@RequestMapping("/deleteIp")
	public void deleteIp(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Ip vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boIp.deleteIp(vo)));
	}
	//添加Ip
	@RequestMapping("/addIp")
	public void addIp(HttpServletRequest req, HttpServletResponse resp, String ipStart,String ipEnd) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boIp.addIp(ipStart,ipEnd)));
	}
	//分配 Ip
	@RequestMapping("/updateIp")
	public void updateIp(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Ip vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boIp.updateIp(vo)));
	}
	//多条件查询
	@RequestMapping("/searchIp")
	public void searchIp(HttpServletRequest req, HttpServletResponse resp, String ip,String mac,String user) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boIp.queryIp(ip,mac,user)));
	}
}
