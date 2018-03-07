package com.fsalliance.core.rest;


import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_Order;
import com.fsalliance.core.bo.CLS_BO_User_Order;
import com.fsalliance.core.util.DateJsonValueProcessor;
import com.fsalliance.core.vo.CLS_VO_Order_I;
import com.fsalliance.core.vo.CLS_VO_User_Order;

@Controller
@RequestMapping("/userOrder")
public class CLS_REST_Order {
	
	@Resource(name = "boUserOrder")
	private CLS_BO_User_Order boUserOrder;
	
	@Resource(name = "boOrder")
	private CLS_BO_Order boOrder;
	
	@RequestMapping("/addOrderTaobao")
	public void addOrderTaobao(HttpServletRequest req, HttpServletResponse resp, CLS_VO_User_Order userOrder) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().print(JSONObject.fromObject(boUserOrder.addOrderTaobao(userOrder),jsonConfig)); 
	}
	

	@RequestMapping("/getOrderByType")
	public void getOrderByType(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Order_I order) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().print(JSONObject.fromObject(boOrder.getOrderByType(order),jsonConfig)); 
	}
}
