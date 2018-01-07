package com.wk.spider.ip.entity;

import java.util.List;

public class IpInformation {
	List<Ips> data;
	Integer status;
	String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Ips> getData() {
		return data;
	}
	public void setData(List<Ips> data) {
		this.data = data;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "bb [status=" + status + ", message=" + message + "]";
	}
	
	
	
	
}
