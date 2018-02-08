package com.fsalliance.core.vo;


public class CLS_VO_Ip {
	// id
	private String id;
	// ip地址
	private String ipName;
	// ip地址
	private String macName;
	// 使用者
	private String userName;
	// 虚拟机状态
	private int ipType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIpName() {
		return ipName;
	}
	public void setIpName(String ipName) {
		this.ipName = ipName;
	}
	public String getMacName() {
		return macName;
	}
	public void setMacName(String macName) {
		this.macName = macName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIpType() {
		return ipType;
	}
	public void setIpType(int ipType) {
		this.ipType = ipType;
	}
}
 