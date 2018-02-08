package com.fsalliance.core.vo;

import java.util.ArrayList;
import java.util.List;

public class CLS_VO_FolderTree {
	
	private String id;
	private String parentId;
	private String caption;
	private long typeId;
	private String filePath;
	private double fileSize;
	private String fileType;
	private List<CLS_VO_FolderTree> items = new ArrayList<CLS_VO_FolderTree>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<CLS_VO_FolderTree> getItems() {
		return items;
	}
	public void setItems(List<CLS_VO_FolderTree> items) {
		this.items = items;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public double getFileSize() {
		return fileSize;
	}
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
}
