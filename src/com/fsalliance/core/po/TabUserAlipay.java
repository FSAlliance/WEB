package com.fsalliance.core.po;

/**
 * TabUserAlipay entity. @author MyEclipse Persistence Tools
 */

public class TabUserAlipay implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String SAlipayOrderid;

	private String SUserId;

	// Constructors

	/** default constructor */
	public TabUserAlipay() {
	}

	/** full constructor */
	public TabUserAlipay(String SAlipayOrderid, String SUserId) {
		this.SAlipayOrderid = SAlipayOrderid;
		this.SUserId = SUserId;
	}

	// Property accessors

	public String getSAlipayOrderid() {
		return this.SAlipayOrderid;
	}

	public void setSAlipayOrderid(String SAlipayOrderid) {
		this.SAlipayOrderid = SAlipayOrderid;
	}

	public String getSUserId() {
		return this.SUserId;
	}

	public void setSUserId(String SUserId) {
		this.SUserId = SUserId;
	}

}