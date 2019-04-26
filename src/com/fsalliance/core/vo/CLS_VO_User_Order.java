package com.fsalliance.core.vo;

/**
 * 用户绑定的淘宝号（订单号）
 * @author yuanxueyuan
 */
public class CLS_VO_User_Order {

	private String orderId;

	private String userId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "CLS_VO_USER_ALIPAYNUM [orderId=" + orderId + ", userId="
				+ userId + "]";
	}
	
	
}
