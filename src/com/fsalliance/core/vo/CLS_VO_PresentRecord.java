package com.fsalliance.core.vo;


public class CLS_VO_PresentRecord {
	// id
	private String id;
	private int state; //提现状态  0 提现中，1 已提现
	private String presentTime; //提现时间
	private String presentMoneny; //提现金额
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPresentTime() {
		return presentTime;
	}
	public void setPresentTime(String presentTime) {
		this.presentTime = presentTime;
	}
	public String getPresentMoneny() {
		return presentMoneny;
	}
	public void setPresentMoneny(String presentMoneny) {
		this.presentMoneny = presentMoneny;
	}
	
}
 