package com.fsalliance.core.vo;

import java.util.Set;

public class CLS_VO_Base {

	private Set<Integer>  auths;  /*某资源的权限列表*/
	
    private Long objType; /*某类权限类型*/
    
	public Set<Integer> getAuths() {
		return auths;
	}
	public void setAuths(Set<Integer> auths) {
		this.auths = auths;
	}
	public Long getObjType() {
		return objType;
	}
	public void setObjType(Long objType) {
		this.objType = objType;
	}	
}
