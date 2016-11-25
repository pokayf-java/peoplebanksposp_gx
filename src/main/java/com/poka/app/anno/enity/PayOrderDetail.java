package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ORDERDETAIL")
@IdClass(value = PayOrderDetailPK.class)
public class PayOrderDetail implements Serializable {
	
//	private Integer did;
	private String detailId;
	private String provId;
	private String unitId;
	private String orderId;
	private String currencyId;
	private Integer currencyKind;
//	private Integer currencyMoney;
	private Double currencyMoney;
	private Integer bagCount;
	private Integer bundleCount;
	private Integer detailState;
	
	
	@Override
	public String toString() {
		return "PayOrderDetail [detailId=" + detailId
				+ ", provId=" + provId + ", unitId=" + unitId + ", orderId="
				+ orderId + ", currencyId=" + currencyId + ", currencyKind="
				+ currencyKind + ", currencyMoney=" + currencyMoney
				+ ", bagCount=" + bagCount + ", bundleCount=" + bundleCount
				+ ", detailState=" + detailState + "]";
	}
//	@Id
//	@Column(name = "DID")
//	public Integer getDid() {
//		return did;
//	}
//	public void setDid(Integer did) {
//		this.did = did;
//	}
	
	@Column(name = "DETAILID",length=20)
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	@Id
	@Column(name = "PROVID",length=2)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Id
	@Column(name = "UNITID",length=4)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Id
	@Column(name = "ORDERID",length=20)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Id
	@Column(name = "CURRENCYID",length=2)
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	
	@Id
	@Column(name = "CURRENCYKIND")
	public Integer getCurrencyKind() {
		return currencyKind;
	}
	public void setCurrencyKind(Integer currencyKind) {
		this.currencyKind = currencyKind;
	}
	
	@Column(name = "CURRENCYMONEY")
	public Double getCurrencyMoney() {
		return currencyMoney;
	}
	public void setCurrencyMoney(Double currencyMoney) {
		this.currencyMoney = currencyMoney;
	}
	
	@Column(name = "BAGCOUNT")
	public Integer getBagCount() {
		return bagCount;
	}
	public void setBagCount(Integer bagCount) {
		this.bagCount = bagCount;
	}
	
	@Column(name = "BUNDLECOUNT")
	public Integer getBundleCount() {
		return bundleCount;
	}
	public void setBundleCount(Integer bundleCount) {
		this.bundleCount = bundleCount;
	}
	
	@Column(name = "DETAILSTATE")
	public Integer getDetailState() {
		return detailState;
	}
	public void setDetailState(Integer detailState) {
		this.detailState = detailState;
	}
	
	

}
