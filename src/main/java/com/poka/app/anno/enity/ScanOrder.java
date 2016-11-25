package com.poka.app.anno.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * 扫描订单实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SCANORDER")
@IdClass(ScanOrderPK.class)
public class ScanOrder implements Serializable{
	private String provId;
	private String unitId;
	private String machId;
	private String orderId;
	
	@Id
	@Column(name="PROVID",length=10)
	public String getProvId() {
		return provId;
	}
	public void setProvId(String provId) {
		this.provId = provId;
	}
	
	@Id
	@Column(name="UNITID",length=10)
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	@Id
	@Column(name="MACHID",length=20)
	public String getMachId() {
		return machId;
	}
	public void setMachId(String machId) {
		this.machId = machId;
	}
	
	@Id
	@Column(name="ORDERID",length=30)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	

}
