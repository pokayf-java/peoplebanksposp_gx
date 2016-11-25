package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PayOrderDetailPK implements Serializable {
//	private Integer did;
	private String provId;
	private String unitId;
	private String orderId;
	private String currencyId;
	private Integer currencyKind;
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PayOrderDetailPK){
			PayOrderDetailPK pk = (PayOrderDetailPK)obj;
//			if(pk.getDid() == this.did 
			if(pk.getProvId().equals(this.getProvId())
					&& pk.getOrderId().equals(this.getOrderId())
					&& pk.getUnitId().equals(this.getUnitId())
					&& pk.getCurrencyId().equals(this.getCurrencyId())
					&& pk.getCurrencyKind() == this.getCurrencyKind()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return this.getOrderId().hashCode();
	}
	
//	@Id
//	@Column(name = "DID")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Integer getDid() {
//		return did;
//	}
//	public void setDid(Integer did) {
//		this.did = did;
//	}
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public Integer getCurrencyKind() {
		return currencyKind;
	}
	public void setCurrencyKind(Integer currencyKind) {
		this.currencyKind = currencyKind;
	}
}
