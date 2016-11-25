package com.poka.app.anno.enity;

import java.io.Serializable;

public class PayOrderPK implements Serializable {

	// private Integer id;
	private String provId;
	private String unitId;
	private String orderId;

	// @Id
	// @Column(name = "Did")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// public Integer getId() {
	// return id;
	// }

	// public void setId(Integer id) {
	// this.id = id;
	// }

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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PayOrderPK) {
			PayOrderPK pk = (PayOrderPK) obj;
			// if(pk.getId() == this.id
			if (pk.getProvId().equals(this.getProvId()) && pk.getOrderId().equals(this.getOrderId())
					&& pk.getUnitId().equals(this.getUnitId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getOrderId().hashCode();
	}
}
