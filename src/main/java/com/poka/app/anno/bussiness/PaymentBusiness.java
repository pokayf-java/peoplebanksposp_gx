package com.poka.app.anno.bussiness;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.vo.PaymentVo;

@Component
public class PaymentBusiness {
	Logger logger = Logger.getLogger(PaymentBusiness.class);
	private OrderInfoService orderInfoService;

	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
	public boolean makePayment(PaymentVo payment){
		logger.info("正在处理交款信息");
		return orderInfoService.savePaymentVo(payment);
	}
}
