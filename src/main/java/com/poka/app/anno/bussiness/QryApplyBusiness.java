package com.poka.app.anno.bussiness;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.enity.QryApply;

@Component
public class QryApplyBusiness {
	Logger logger = Logger.getLogger(QryApplyBusiness.class);
	private OrderInfoService orderInfoService;

	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
	public boolean makeQryApply(QryApply qryApply){
		logger.info("正在处理查询申请信息");
		return orderInfoService.saveQryApply(qryApply);
	}
}
