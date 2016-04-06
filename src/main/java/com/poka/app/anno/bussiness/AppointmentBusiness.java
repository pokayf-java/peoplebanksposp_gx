package com.poka.app.anno.bussiness;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankInfoService;
import com.poka.app.anno.base.service.impl.OrderInfoService;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
import com.poka.app.util.CxfUtil;
import com.poka.app.vo.AppointmenResult;
import com.poka.app.vo.AppointmentVo;

@Component
public class AppointmentBusiness {
	Logger logger = Logger.getLogger(AppointmentBusiness.class);
	private OrderInfoService orderInfoService;
	private BankInfoService bankInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}
	
	@Autowired
	@Qualifier("orderInfoService")
	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}
	
	@Autowired
	@Qualifier("bankInfoService")
	public void setBankInfoService(BankInfoService bankInfoService) {
		this.bankInfoService = bankInfoService;
	}
	
	public void handAppointment(){
		List<OrderInfo> orders = this.orderInfoService.getUnsendOrder(OrderType.APPOINTMENT);
		if(orders.size()<=0){
			return;
		}
		logger.info("未处理订单数量为:"+orders.size());
		for(OrderInfo order:orders){
			String orderId = order.getOrderId().trim();
			logger.info("处理订单号:"+orderId);
			
			AppointmenResult vo = orderInfoService.getAppointmenResult(orderId);
			
			if(vo.getBankId() == null){
				logger.info("处理订单号:"+orderId+" 失败,缺少银行号!");
				orderInfoService.updateOrderInfoState(order, StateType.ERROR);
				continue;
			}
			String bankIp  = bankInfoService.getBankIp(vo.getBankId().trim());
			
			if(bankIp == null){
				logger.info("处理订单号:"+orderId+" 失败,缺少银行IP!");
				orderInfoService.updateOrderInfoState(order, StateType.ERROR);
				continue;
			}
		//	System.out.println("bankip :"+bankIp+"   no:"+vo.getBankId());
			ICBPospSW service = cxfUtil.getCxfClient(ICBPospSW.class, cxfUtil.getUrl(bankIp,cxfUtil.getPort()));
			cxfUtil.recieveTimeOutWrapper(service);
		
			boolean result = Boolean.FALSE;
			try{
				result = service.handleAppointmen(vo);
			}catch(Exception ex){
				logger.info("连接服务器失败!");
			}
			if (result) {
				logger.info("处理預約取款结果订单:" + orderId+"  成功");
				this.orderInfoService.updateOrderInfoState(order,
						StateType.SENDED);
			}else{
				logger.info("处理預約取款结果订单:" + orderId+"  失败");
			}
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean makeAppointment(AppointmentVo appointment){	
		logger.info("正在处理预约交款信息");
		return orderInfoService.saveAppointmentVo(appointment);
	}
}
