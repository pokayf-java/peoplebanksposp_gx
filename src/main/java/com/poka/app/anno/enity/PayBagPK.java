package com.poka.app.anno.enity;

import java.io.Serializable;

public class PayBagPK implements Serializable {
	// private Integer id;
	private String provId;
	private String unitId;
	private String bagCode;
	private String orderId;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PayBagPK) {
			PayBagPK pk = (PayBagPK) obj;
			// if(pk.getId() == this.id
			if (pk.getProvId().equals(this.getProvId()) && pk.getOrderId().equals(this.getOrderId())
					&& pk.getUnitId().equals(this.getUnitId()) && pk.getBagCode().equals(this.getBagCode())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getOrderId().hashCode();
	}

//	@Id
//	@Column(name = "Did")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
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

	public String getBagCode() {
		return bagCode;
	}

	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
