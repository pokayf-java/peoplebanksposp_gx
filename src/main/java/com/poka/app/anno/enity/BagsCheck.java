package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author 
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BAGSCHECK")
public class BagsCheck implements Serializable{
	
	private Integer did;
	private String provId;
	private String unitId;
	private String bagCode;
	private String orderId;
	private String currencyId;
	private Integer currencyKind;
	private String countId;
	private String bagId;
	private String checkId;
	private String msg;
	private String areaCode;
	
	@Id
	@Column(name = "DID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	
	@Column(name = "PROVID", length = 2)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Column(name = "UNITID", length = 20)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Column(name = "BAGCODE", length = 20)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	
	@Column(name = "ORDERID", length = 20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "CURRENCYID", length = 2)
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	
	@Column(name = "CURRENCYKIND")
	public Integer getCurrencyKind() {
		return currencyKind;
	}
	public void setCurrencyKind(Integer currencyKind) {
		this.currencyKind = currencyKind;
	}
	
	@Column(name = "COUNTID", length = 8)
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
	}
	
	@Column(name = "BAGID", length = 8)
	public String getBagId() {
		return bagId;
	}
	public void setBagId(String bagId) {
		this.bagId = bagId;
	}
	
	@Column(name = "CHECKID", length = 8)
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	
	@Column(name = "MSG", length = 50)
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(name = "AREACODE", length = 10)
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
