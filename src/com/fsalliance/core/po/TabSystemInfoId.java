package com.fsalliance.core.po;

/**
 * TabSystemInfoId entity. @author MyEclipse Persistence Tools
 */

public class TabSystemInfoId implements java.io.Serializable {

	// Fields

	private Long IId;
	private String SSystemName;
	private String SSystemUsername;
	private String SSystemPassword;
	private Long SSystemMaxLevel;

	// Constructors

	/** default constructor */
	public TabSystemInfoId() {
	}

	/** minimal constructor */
	public TabSystemInfoId(Long IId) {
		this.IId = IId;
	}

	/** full constructor */
	public TabSystemInfoId(Long IId, String SSystemName,
			String SSystemUsername, String SSystemPassword, Long SSystemMaxLevel) {
		this.IId = IId;
		this.SSystemName = SSystemName;
		this.SSystemUsername = SSystemUsername;
		this.SSystemPassword = SSystemPassword;
		this.SSystemMaxLevel = SSystemMaxLevel;
	}

	// Property accessors

	public Long getIId() {
		return this.IId;
	}

	public void setIId(Long IId) {
		this.IId = IId;
	}

	public String getSSystemName() {
		return this.SSystemName;
	}

	public void setSSystemName(String SSystemName) {
		this.SSystemName = SSystemName;
	}

	public String getSSystemUsername() {
		return this.SSystemUsername;
	}

	public void setSSystemUsername(String SSystemUsername) {
		this.SSystemUsername = SSystemUsername;
	}

	public String getSSystemPassword() {
		return this.SSystemPassword;
	}

	public void setSSystemPassword(String SSystemPassword) {
		this.SSystemPassword = SSystemPassword;
	}

	public Long getSSystemMaxLevel() {
		return this.SSystemMaxLevel;
	}

	public void setSSystemMaxLevel(Long SSystemMaxLevel) {
		this.SSystemMaxLevel = SSystemMaxLevel;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TabSystemInfoId))
			return false;
		TabSystemInfoId castOther = (TabSystemInfoId) other;

		return ((this.getIId() == castOther.getIId()) || (this.getIId() != null
				&& castOther.getIId() != null && this.getIId().equals(
				castOther.getIId())))
				&& ((this.getSSystemName() == castOther.getSSystemName()) || (this
						.getSSystemName() != null
						&& castOther.getSSystemName() != null && this
						.getSSystemName().equals(castOther.getSSystemName())))
				&& ((this.getSSystemUsername() == castOther
						.getSSystemUsername()) || (this.getSSystemUsername() != null
						&& castOther.getSSystemUsername() != null && this
						.getSSystemUsername().equals(
								castOther.getSSystemUsername())))
				&& ((this.getSSystemPassword() == castOther
						.getSSystemPassword()) || (this.getSSystemPassword() != null
						&& castOther.getSSystemPassword() != null && this
						.getSSystemPassword().equals(
								castOther.getSSystemPassword())))
				&& ((this.getSSystemMaxLevel() == castOther
						.getSSystemMaxLevel()) || (this.getSSystemMaxLevel() != null
						&& castOther.getSSystemMaxLevel() != null && this
						.getSSystemMaxLevel().equals(
								castOther.getSSystemMaxLevel())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getIId() == null ? 0 : this.getIId().hashCode());
		result = 37
				* result
				+ (getSSystemName() == null ? 0 : this.getSSystemName()
						.hashCode());
		result = 37
				* result
				+ (getSSystemUsername() == null ? 0 : this.getSSystemUsername()
						.hashCode());
		result = 37
				* result
				+ (getSSystemPassword() == null ? 0 : this.getSSystemPassword()
						.hashCode());
		result = 37
				* result
				+ (getSSystemMaxLevel() == null ? 0 : this.getSSystemMaxLevel()
						.hashCode());
		return result;
	}

}