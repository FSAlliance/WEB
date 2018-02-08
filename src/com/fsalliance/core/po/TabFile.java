package com.fsalliance.core.po;

import java.sql.Timestamp;

/**
 * TabFile entity. @author MyEclipse Persistence Tools
 */

public class TabFile implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String SId;

	private String SParentId;

	private String SFilePath;

	private String SFileName;

	private Long ITypeId;

	private String SAuthor;

	private Long SFileSize;

	private String SFileType;

	private Timestamp SDate;

	// Constructors

	/** default constructor */
	public TabFile() {
	}

	/** minimal constructor */
	public TabFile(String SId) {
		this.SId = SId;
	}

	/** full constructor */
	public TabFile(String SId, String SParentId, String SFilePath,
			String SFileName, Long ITypeId, String SAuthor, Long SFileSize,
			String SFileType, Timestamp SDate) {
		this.SId = SId;
		this.SParentId = SParentId;
		this.SFilePath = SFilePath;
		this.SFileName = SFileName;
		this.ITypeId = ITypeId;
		this.SAuthor = SAuthor;
		this.SFileSize = SFileSize;
		this.SFileType = SFileType;
		this.SDate = SDate;
	}

	// Property accessors

	public String getSId() {
		return this.SId;
	}

	public void setSId(String SId) {
		this.SId = SId;
	}

	public String getSParentId() {
		return this.SParentId;
	}

	public void setSParentId(String SParentId) {
		this.SParentId = SParentId;
	}

	public String getSFilePath() {
		return this.SFilePath;
	}

	public void setSFilePath(String SFilePath) {
		this.SFilePath = SFilePath;
	}

	public String getSFileName() {
		return this.SFileName;
	}

	public void setSFileName(String SFileName) {
		this.SFileName = SFileName;
	}

	public Long getITypeId() {
		return this.ITypeId;
	}

	public void setITypeId(Long ITypeId) {
		this.ITypeId = ITypeId;
	}

	public String getSAuthor() {
		return this.SAuthor;
	}

	public void setSAuthor(String SAuthor) {
		this.SAuthor = SAuthor;
	}

	public Long getSFileSize() {
		return this.SFileSize;
	}

	public void setSFileSize(Long SFileSize) {
		this.SFileSize = SFileSize;
	}

	public String getSFileType() {
		return this.SFileType;
	}

	public void setSFileType(String SFileType) {
		this.SFileType = SFileType;
	}

	public Timestamp getSDate() {
		return this.SDate;
	}

	public void setSDate(Timestamp SDate) {
		this.SDate = SDate;
	}

}