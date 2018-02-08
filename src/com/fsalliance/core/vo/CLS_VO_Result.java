package com.fsalliance.core.vo;

public class CLS_VO_Result {
	private int ret;
	private Object content;

	public CLS_VO_Result() {
		ret = -1;
		content = null;
	}

	public CLS_VO_Result(int ret, Object content) {
		super();
		this.ret = ret;
		this.content = content;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
