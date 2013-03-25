package com.ytk.models;

import java.util.List;

public class ResponseHeader {
	
	private Integer status;
	private Integer QTime;
	private Params params;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public int getQTime() {
		return QTime;
	}
	public void setQTime(Integer qTime) {
		QTime = qTime;
	}
	public Params getParams() {
		return params;
	}
	public void setParams(Params params) {
		this.params = params;
	}


}
