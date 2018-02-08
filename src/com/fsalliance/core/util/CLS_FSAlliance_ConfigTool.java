package com.fsalliance.core.util;

import java.util.ResourceBundle;

/**
 * ProjectName：PrisonWebV8   
 * TypeName：ConfigTool
 *  读取 SRC 下的 资源文件 公共类 
 * @author ：SZMIN
 * @Date：Feb 11, 2012 
 * @version：1.0 
 */
public class CLS_FSAlliance_ConfigTool {

	private static final String BUNDLE_NAME = "config";
	private static final ResourceBundle RESOURCE_BUNDEL = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * 通过 键获取 配置文件的 属性值
	 *	@param key
	 *	@return String
	 *  TODO   getValue
	 * @author：SZM  
	 * @Date：Feb 11, 2012 3:34:28 PM
	 */
	public static String getValue(String key) {
		return RESOURCE_BUNDEL.getString(key);
	}

}
