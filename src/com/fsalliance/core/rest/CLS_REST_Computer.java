package com.fsalliance.core.rest;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.bo.CLS_BO_Computer;
import com.fsalliance.core.util.DateJsonValueProcessor;
import com.fsalliance.core.vo.CLS_VO_Computer;

@Controller
@RequestMapping("/computer")
public class CLS_REST_Computer {

	@Resource(name = "boComputer")
	private CLS_BO_Computer boComputer;
	
	//查询
	@RequestMapping("/queryComputer")
	public void queryComputer(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		JsonConfig jsonConfig = new JsonConfig();
    	jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
    	resp.getWriter().print(JSONObject.fromObject(boComputer.queryComputer(),jsonConfig));
	}
	//删除
	@RequestMapping("/deleteComputer")
	public void deleteComputer(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Computer vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boComputer.deleteComputer(vo)));
	}
	//添加虚拟机
	@RequestMapping("/addComputer")
	public void addComputer(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Computer vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boComputer.addComputer(vo)));
	}
	//修改虚拟机
	@RequestMapping("/updateComputer")
	public void updateComputer(HttpServletRequest req, HttpServletResponse resp, CLS_VO_Computer vo) throws Exception{
		resp.getWriter().print(JSONObject.fromObject(boComputer.updateComputer(vo)));
	}
}
