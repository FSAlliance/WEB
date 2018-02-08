package com.fsalliance.core.po;

/**
 * TabIp entity. @author MyEclipse Persistence Tools
 */

public class TabIp implements java.io.Serializable {

	// Fields

	private String SId;
	private String SName;
	private String SIp;
	private String SMac;
	private Long IType;

	// Constructors

	/** default constructor */
	public TabIp() {
	}

	/** minimal constructor */
	public TabIp(String SId, String SIp) {
		this.SId = SId;
		this.SIp = SIp;
	}

	/** full constructor */
	public TabIp(String SId, String SName, String SIp, String SMac, Long IType) {
		this.SId = SId;
		this.SName = SName;
		this.SIp = SIp;
		this.SMac = SMac;
		this.IType = IType;
	}

	// Property accessors

	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSIp() {
		return this.SIp;
	}

	public void setSIp(String SIp) {
		this.SIp = SIp;
	}

	public String getSMac() {
		return this.SMac;
	}

	public void setSMac(String SMac) {
		this.SMac = SMac;
	}

	public Long getIType() {
		return this.IType;
	}

	public void setIType(Long IType) {
		this.IType = IType;
	}

}