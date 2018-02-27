package com.fsalliance.core.rest;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_User_Order;
import com.fsalliance.core.vo.CLS_VO_USER_ORDER;

@Controller
@RequestMapping("/userOrder")
public class CLS_REST_Order {
	
	@Resource(name = "boUserOrder")
	private CLS_BO_User_Order boUserOrder;
	
	@RequestMapping("/addOrderTaobao")
	public void addOrderTaobao(HttpServletRequest req, HttpServletResponse resp, CLS_VO_USER_ORDER userOrder) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boUserOrder.addOrderTaobao(userOrder))); 
	}
}
