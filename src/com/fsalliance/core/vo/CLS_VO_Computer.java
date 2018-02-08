package com.fsalliance.core.vo;

import java.sql.Timestamp;

public class CLS_VO_Computer {
	// 虚拟机id
	private String id;
	// 虚拟机名称
	private String computerName;
	// ip
	private String computerIp;
	// 使用者
	private String userName;
	// 申请开时间 
	private Timestamp startTime;
	// 结束时间
	private Timestamp endTime;
	// 虚拟机状态
	private long computerType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getComputerIp() {
		return computerIp;
	}
	public void setComputerIp(String computerIp) {
		this.computerIp = computerIp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public long getComputerType() {
		return computerType;
	}
	public void setComputerType(long computerType) {
		this.computerType = computerType;
	}
}
 