package com.fsalliance.core.po;

import java.sql.Timestamp;

/**
 * TabCashRecord entity. @author MyEclipse Persistence Tools
 */

public class TabCashRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer IId;

	private String SUserId;

	private Integer ICashStatus;

	private Timestamp dtCashTime;

	private Double DCashBalanceNum;

	// Constructors

	/** default constructor */
	public TabCashRecord() {
	}

	/** minimal constructor */
	public TabCashRecord(Integer IId, String SUserId, Integer ICashStatus) {
		this.IId = IId;
		this.SUserId = SUserId;
		this.ICashStatus = ICashStatus;
	}

	/** full constructor */
	public TabCashRecord(Integer IId, String SUserId, Integer ICashStatus,
			Timestamp dtCashTime, Double DCashBalanceNum) {
		this.IId = IId;
		this.SUserId = SUserId;
		this.ICashStatus = ICashStatus;
		this.dtCashTime = dtCashTime;
		this.DCashBalanceNum = DCashBalanceNum;
	}

	// Property accessors

	public Integer getIId() {
		return this.IId;
	}

	public void setIId(Integer IId) {
		this.IId = IId;
	}

	public String getSUserId() {
		return this.SUserId;
	}

	public void setSUserId(String SUserId) {
		this.SUserId = SUserId;
	}

	public Integer getICashStatus() {
		return this.ICashStatus;
	}

	public void setICashStatus(Integer ICashStatus) {
		this.ICashStatus = ICashStatus;
	}

	public Timestamp getDtCashTime() {
		return this.dtCashTime;
	}

	public void setDtCashTime(Timestamp dtCashTime) {
		this.dtCashTime = dtCashTime;
	}

	public Double getDCashBalanceNum() {
		return this.DCashBalanceNum;
	}

	public void setDCashBalanceNum(Double DCashBalanceNum) {
		this.DCashBalanceNum = DCashBalanceNum;
	}

}