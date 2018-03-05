package com.fsalliance.core.po;

/**
 * TabSystemInfo entity. @author MyEclipse Persistence Tools
 */

public class TabSystemInfo implements java.io.Serializable {

	// Fields

	private TabSystemInfoId id;

	// Constructors

	/** default constructor */
	public TabSystemInfo() {
	}

	/** full constructor */
	public TabSystemInfo(TabSystemInfoId id) {
		this.id = id;
	}

	// Property accessors

	public TabSystemInfoId getId() {
		return this.id;
	}

	public void setId(TabSystemInfoId id) {
		this.id = id;
	}

}