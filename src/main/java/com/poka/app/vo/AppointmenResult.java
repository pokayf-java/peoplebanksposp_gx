package com.poka.app.vo;

import java.io.Serializable;

public class AppointmenResult implements Serializable {
	private String orderId;
	private int state;
	private String checkRemark;
	private String bankId;
	
	public AppointmenResult(){}
	@Override
	public String toString() {
		return "AppointmenResult [orderId=" + orderId + ", state=" + state
				+ ", checkRemark=" + checkRemark + ", bankId=" + bankId + "]";
	}
	
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCheckRemark() {
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark) {
		this.checkRemark = checkRemark;
	}
}
