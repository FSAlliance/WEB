package com.fsalliance.core.util;

public class CLS_FSAlliance_Error {
	
	public static final int ERROR_OK = 0;					//成功
	public static final int ERROR_PARAM = -1;				//参数错误
	public static final int ERROR_DB_CONN = -2;				//数据库连接错误
	public static final int ERROR_DB_EXCEPTION = -3;		//数据库异常
	public static final int ERROR_DB_EXIST = -4;			//数据已存在
	public static final int ERROR_USER_NOT_EXIST = -5;		//用户不存在，或已更新
	public static final int ERROR_PASSWORD = -6;			//密码错误
	public static final int ERROR_USER_AGAIN = -7;			//用户已登录
	public static final int ERROR_SAME_USER_LOGON = -8;	    //同名用户登录	
	public static final int ERROR_LOGINMS_FAILED = -9;		//登录失败 
	public static final int ERROR_LOGINMS_TIMEOUT = -10;	//登录超时
	public static final int ERROR_REQUEST_FAILED = -11;		//请求失败
	public static final int ERROR_REQUEST_TIMEOUT = -12;	//请求超时
    public static final int ERROR_USER_INFO = -13;          //用户名或密码错误
    public static final int ERROR_PASSWORD_MODIFY_FAILED = -14;   //密码修改失败
	public static final int ERROR_USERID_EXIST = -15;      //用户id已经存在
	public static final int ERROR_ORDER_MAX = -16;      //超出最大个数
	public static final int ERROR_ORDER_HAVE = -17;      //淘宝账号已经存在
	public static final int ERROR_ORDER_NO_HAVE = -18;      //没有该订单
}
