package com.fsalliance.core.po;

import java.sql.Timestamp;

/**
 * TabUser entity. @author MyEclipse Persistence Tools
 */

public class TabUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String SUserId;

	private String SPhoneNum;

	private String SName;

	private String SPath;

	private String SPassword;

	private Timestamp dtCreateTime;

	private Timestamp dtLoginTime;

	private String SUserPic;

	private String SAlipayNum;

	private String SInviteNum;

	private String SParentId;

	private Double DBalanceNum;

	private Double DCashing;

	private Double DCashed;

	private Integer IScoreNum;

	// Constructors

	/** default constructor */
	public TabUser() {
	}

	/** minimal constructor */
	public TabUser(String SUserId, String SPhoneNum, String SName,
			String SPath, String SPassword, String SUserPic, String SAlipayNum,
			String SInviteNum, String SParentId, Double DBalanceNum,
			Double DCashing, Double DCashed, Integer IScoreNum) {
		this.SUserId = SUserId;
		this.SPhoneNum = SPhoneNum;
		this.SName = SName;
		this.SPath = SPath;
		this.SPassword = SPassword;
		this.SUserPic = SUserPic;
		this.SAlipayNum = SAlipayNum;
		this.SInviteNum = SInviteNum;
		this.SParentId = SParentId;
		this.DBalanceNum = DBalanceNum;
		this.DCashing = DCashing;
		this.DCashed = DCashed;
		this.IScoreNum = IScoreNum;
	}

	/** full constructor */
	public TabUser(String SUserId, String SPhoneNum, String SName,
			String SPath, String SPassword, Timestamp dtCreateTime,
			Timestamp dtLoginTime, String SUserPic, String SAlipayNum,
			String SInviteNum, String SParentId, Double DBalanceNum,
			Double DCashing, Double DCashed, Integer IScoreNum) {
		this.SUserId = SUserId;
		this.SPhoneNum = SPhoneNum;
		this.SName = SName;
		this.SPath = SPath;
		this.SPassword = SPassword;
		this.dtCreateTime = dtCreateTime;
		this.dtLoginTime = dtLoginTime;
		this.SUserPic = SUserPic;
		this.SAlipayNum = SAlipayNum;
		this.SInviteNum = SInviteNum;
		this.SParentId = SParentId;
		this.DBalanceNum = DBalanceNum;
		this.DCashing = DCashing;
		this.DCashed = DCashed;
		this.IScoreNum = IScoreNum;
	}

	// Property accessors

	public String getSUserId() {
		return this.SUserId;
	}

	public void setSUserId(String SUserId) {
		this.SUserId = SUserId;
	}

	public String getSPhoneNum() {
		return this.SPhoneNum;
	}

	public void setSPhoneNum(String SPhoneNum) {
		this.SPhoneNum = SPhoneNum;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSPath() {
		return this.SPath;
	}

	public void setSPath(String SPath) {
		this.SPath = SPath;
	}

	public String getSPassword() {
		return this.SPassword;
	}

	public void setSPassword(String SPassword) {
		this.SPassword = SPassword;
	}

	public Timestamp getDtCreateTime() {
		return this.dtCreateTime;
	}

	public void setDtCreateTime(Timestamp string) {
		this.dtCreateTime = string;
	}

	public Timestamp getDtLoginTime() {
		return this.dtLoginTime;
	}

	public void setDtLoginTime(Timestamp dtLoginTime) {
		this.dtLoginTime = dtLoginTime;
	}

	public String getSUserPic() {
		return this.SUserPic;
	}

	public void setSUserPic(String SUserPic) {
		this.SUserPic = SUserPic;
	}

	public String getSAlipayNum() {
		return this.SAlipayNum;
	}

	public void setSAlipayNum(String SAlipayNum) {
		this.SAlipayNum = SAlipayNum;
	}

	public String getSInviteNum() {
		return this.SInviteNum;
	}

	public void setSInviteNum(String SInviteNum) {
		this.SInviteNum = SInviteNum;
	}

	public String getSParentId() {
		return this.SParentId;
	}

	public void setSParentId(String SParentId) {
		this.SParentId = SParentId;
	}

	public Double getDBalanceNum() {
		return this.DBalanceNum;
	}

	public void setDBalanceNum(Double DBalanceNum) {
		this.DBalanceNum = DBalanceNum;
	}

	public Double getDCashing() {
		return this.DCashing;
	}

	public void setDCashing(Double DCashing) {
		this.DCashing = DCashing;
	}

	public Double getDCashed() {
		return this.DCashed;
	}

	public void setDCashed(Double DCashed) {
		this.DCashed = DCashed;
	}

	public Integer getIScoreNum() {
		return this.IScoreNum;
	}

	public void setIScoreNum(Integer IScoreNum) {
		this.IScoreNum = IScoreNum;
	}

}