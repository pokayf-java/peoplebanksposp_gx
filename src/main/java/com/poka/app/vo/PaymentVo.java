package com.poka.app.vo;

import java.io.Serializable;
import java.util.List;


import com.poka.app.anno.enity.PayBag;
import com.poka.app.anno.enity.PayBundle;
import com.poka.app.anno.enity.PayOrder;
import com.poka.app.anno.enity.PayOrderDetail;

public class PaymentVo implements Serializable {
      private PayOrder payOrder;
      private List<PayOrderDetail> payOrderDetails;
      private List<PayBag> payBags;
      private List<PayBundle> payBundles;
      
    
    public PaymentVo(){}
	@Override
	public String toString() {
		return "PaymentVo [payOrder=" + payOrder + ", payOrderDetails="
				+ payOrderDetails + ", payBags=" + payBags + ", payBundles="
				+ payBundles + "]";
	}
	
	public PayOrder getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(PayOrder payOrder) {
		this.payOrder = payOrder;
	}
	public List<PayOrderDetail> getPayOrderDetails() {
		return payOrderDetails;
	}
	public void setPayOrderDetails(List<PayOrderDetail> payOrderDetails) {
		this.payOrderDetails = payOrderDetails;
	}
	public List<PayBag> getPayBags() {
		return payBags;
	}
	public void setPayBags(List<PayBag> payBags) {
		this.payBags = payBags;
	}
	public List<PayBundle> getPayBundles() {
		return payBundles;
	}
	public void setPayBundles(List<PayBundle> payBundles) {
		this.payBundles = payBundles;
	}
	
}
