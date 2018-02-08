package com.fsalliance.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext context;//声明�?��静�?变量保存 

	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		MyApplicationContextUtil.context = contex;
	}
	
	public static ApplicationContext getContext() {
		return MyApplicationContextUtil.context;
	}
}
