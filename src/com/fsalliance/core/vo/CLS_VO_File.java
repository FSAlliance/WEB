package com.fsalliance.core.vo;

public class CLS_VO_File {
	// id
	private String id;
	// 文件大小
	private long fileSize;
	// 文件名称
	private String fileName;
	//创建者
	private String currentUser;
	//文件夹 ：1 其他：2
	private int type;
	//地址
	private String path;
	//父节点
	private String parentId;
	//文件类型
	private String fileType;
	//文件大小
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CLS_VO_File [currentUser=" + currentUser + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", fileType="
				+ fileType + ", parentId=" + parentId + ", path=" + path
				+ ", type=" + type + "]";
	}

}
