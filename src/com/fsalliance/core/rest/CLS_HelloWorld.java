package com.fsalliance.core.rest;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsalliance.core.util.DateJsonValueProcessor;


  
@Controller  
@RequestMapping("/HelloWorld")  
public class CLS_HelloWorld {  

	@RequestMapping(value="/test")
	public void get(HttpServletRequest req, HttpServletResponse resp) throws Exception  {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		resp.getWriter().println("fdsafdsafd---------");
	}
} 