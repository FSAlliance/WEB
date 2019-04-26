package com.fsalliance.core.vo;

import java.sql.Timestamp;

/**
 * 订单
 * @author yuanxueyuan
 */
public class CLS_VO_Order_I {
	private String userId;//用户ID
	private int orderType;//订单状态
	private int pageNo;//第几页
	private int pageSize;//一页的个数
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "CLS_VO_Order_I [orderType=" + orderType + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", userId=" + userId + "]";
	}
	
}
