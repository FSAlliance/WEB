package com.fsalliance.core.vo;

import java.util.Set;

public class CLS_VO_TB_Favorite {
	
	private long favoritesId;//选品库的id
	private long platForm;//链接形式：1：PC，2：无线，默认：１
	private long pageSize;//页大小，默认20，1~100
	private long adzoneId;//推广位id，
	private String uuid;//自定义输入串，英文和数字组成，长度不能大于12个字符，区分不同的推广渠道
	private long pageNo;//第几页，默认：1，从1开始计数
	public long getFavoritesId() {
		return favoritesId;
	}
	public void setFavoritesId(long favoritesId) {
		this.favoritesId = favoritesId;
	}
	public long getPlatForm() {
		return platForm;
	}
	public void setPlatForm(long platForm) {
		this.platForm = platForm;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public long getAdzoneId() {
		return adzoneId;
	}
	public void setAdzoneId(long adzoneId) {
		this.adzoneId = adzoneId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	
	
	
}
