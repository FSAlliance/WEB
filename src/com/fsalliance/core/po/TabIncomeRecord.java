package com.fsalliance.core.po;

import java.sql.Timestamp;

/**
 * TabIncomeRecord entity. @author MyEclipse Persistence Tools
 */

public class TabIncomeRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer IId;

	private String SUserId;

	private Integer IIncomeStatus;

	private Timestamp dtIncomeTime;

	private Double DIncomeBalanceNum;

	// Constructors

	/** default constructor */
	public TabIncomeRecord() {
	}

	/** minimal constructor */
	public TabIncomeRecord(Integer IId, String SUserId, Integer IIncomeStatus) {
		this.IId = IId;
		this.SUserId = SUserId;
		this.IIncomeStatus = IIncomeStatus;
	}

	/** full constructor */
	public TabIncomeRecord(Integer IId, String SUserId, Integer IIncomeStatus,
			Timestamp dtIncomeTime, Double DIncomeBalanceNum) {
		this.IId = IId;
		this.SUserId = SUserId;
		this.IIncomeStatus = IIncomeStatus;
		this.dtIncomeTime = dtIncomeTime;
		this.DIncomeBalanceNum = DIncomeBalanceNum;
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

	public Integer getIIncomeStatus() {
		return this.IIncomeStatus;
	}

	public void setIIncomeStatus(Integer IIncomeStatus) {
		this.IIncomeStatus = IIncomeStatus;
	}

	public Timestamp getDtIncomeTime() {
		return this.dtIncomeTime;
	}

	public void setDtIncomeTime(Timestamp dtIncomeTime) {
		this.dtIncomeTime = dtIncomeTime;
	}

	public Double getDIncomeBalanceNum() {
		return this.DIncomeBalanceNum;
	}

	public void setDIncomeBalanceNum(Double DIncomeBalanceNum) {
		this.DIncomeBalanceNum = DIncomeBalanceNum;
	}

}