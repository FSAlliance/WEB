package com.fsalliance.core.vo;

import java.sql.Timestamp;

public class CLS_VO_User_I{
	
	private String userId;//用户ID

	private String phoneNum;//登录注册用户名即手机号

	private String userName;//昵称

	private String password;//密码
	
	private String path;//

	private Timestamp createTime;//注册时间

	private Timestamp loginTime;//最近使用时间

	private String userPic;//头像

	private String alipayNum;//支付宝账号

	private String inviteNum;//推荐码

	private String parentId;//父类uuid

	private Double balanceNum;//余额

	private Double cashing;//提现中

	private Double cashed;//已提现

	private Integer scoreNum;//总积分

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getAlipayNum() {
		return alipayNum;
	}

	public void setAlipayNum(String alipayNum) {
		this.alipayNum = alipayNum;
	}

	public String getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(String inviteNum) {
		this.inviteNum = inviteNum;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Double getBalanceNum() {
		return balanceNum;
	}

	public void setBalanceNum(Double balanceNum) {
		this.balanceNum = balanceNum;
	}

	public Double getCashing() {
		return cashing;
	}

	public void setCashing(Double cashing) {
		this.cashing = cashing;
	}

	public Double getCashed() {
		return cashed;
	}

	public void setCashed(Double cashed) {
		this.cashed = cashed;
	}

	public Integer getScoreNum() {
		return scoreNum;
	}

	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}

	@Override
	public String toString() {
		return "CLS_VO_User_I [alipayNum=" + alipayNum + ", balanceNum="
				+ balanceNum + ", cashed=" + cashed + ", cashing=" + cashing
				+ ", createTime=" + createTime + ", inviteNum=" + inviteNum
				+ ", loginTime=" + loginTime + ", parentId=" + parentId
				+ ", password=" + password + ", path=" + path + ", phoneNum="
				+ phoneNum + ", scoreNum=" + scoreNum + ", userId=" + userId
				+ ", userName=" + userName + ", userPic=" + userPic + "]";
	}

	
}
