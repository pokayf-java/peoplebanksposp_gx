package com.poka.app.anno.enity;

import java.io.Serializable;

public class ScanOrderPK implements Serializable {

	private String provId;
	private String unitId;
	private String machId;
	private String orderId;

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

	public String getMachId() {
		return machId;
	}

	public void setMachId(String machId) {
		this.machId = machId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (this.getProvId() + this.getUnitId() + this.getMachId() + this.getOrderId()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof ScanOrderPK) {
			ScanOrderPK pk = (ScanOrderPK) obj;
			if (pk.getProvId().equals(this.provId) && pk.getUnitId().equals(this.unitId)
					&& pk.getMachId().equals(this.machId) && pk.getOrderId().equals(this.orderId)) {
				return true;
			}

		}
		return false;
	}

}
