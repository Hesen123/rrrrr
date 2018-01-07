package com.wk.spider.ip.entity;
public class Ips {
	String requestTime;
	String sourceip;
	Integer tagId;
	String targetIp;
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getSourceip() {
		return sourceip;
	}
	public void setSourceip(String sourceip) {
		this.sourceip = sourceip;
	}
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	@Override
	public String toString() {
		return "Ips [requestTime=" + requestTime + ", sourceip=" + sourceip + ", tagId=" + tagId + ", targetIp="
				+ targetIp + "]";
	}
	
}
