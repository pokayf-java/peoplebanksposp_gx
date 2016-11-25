package com.poka.app.anno.base.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poka.app.anno.base.dao.impl.PayBagDao;
import com.poka.app.anno.base.dao.impl.PayBundleDao;
import com.poka.app.anno.base.dao.impl.PayOrderDao;
import com.poka.app.anno.base.dao.impl.PayOrderDetailDao;
import com.poka.app.anno.base.dao.impl.QryApplyDao;
import com.poka.app.anno.base.dao.impl.ReserveDetailDao;
import com.poka.app.anno.base.dao.impl.ReserveMainDao;
import com.poka.app.anno.enity.OrderInfo;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.anno.enity.ReserveDetail;
import com.poka.app.anno.enity.ReserveMain;
import com.poka.app.enumtype.OrderType;
import com.poka.app.enumtype.StateType;
import com.poka.app.vo.AppointmenResult;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@Service
public class OrderInfoService extends BaseService<OrderInfo, Integer> {
	private ReserveMainDao reserveMainDao;
	private ReserveDetailDao reserveDetailDao;
	
	private PayOrderDao payOrderDao;
	private PayOrderDetailDao payOrderDetailDao;
	private PayBagDao  payBagDao;
	private PayBundleDao payBundleDao;
	private QryApplyDao qryApplyDao;
	
	
	@Autowired
	public void setQryApplyDao(QryApplyDao qryApplyDao) {
		this.qryApplyDao = qryApplyDao;
	}
	
	@Autowired
	public void setPayOrderDao(PayOrderDao payOrderDao) {
		this.payOrderDao = payOrderDao;
	}
	
	@Autowired
	public void setPayOrderDetailDao(PayOrderDetailDao payOrderDetailDao) {
		this.payOrderDetailDao = payOrderDetailDao;
	}
	@Autowired
	public void setPayBagDao(PayBagDao payBagDao) {
		this.payBagDao = payBagDao;
	}
	@Autowired
	public void setPayBundleDao(PayBundleDao payBundleDao) {
		this.payBundleDao = payBundleDao;
	}
	@Autowired
	public void setReserveMainDao(ReserveMainDao reserveMainDao) {
		this.reserveMainDao = reserveMainDao;
	}
	@Autowired
	public void setReserveDetailDao(ReserveDetailDao reserveDetailDao) {
		this.reserveDetailDao = reserveDetailDao;
	}
    
	public void updateOrderInfoState(OrderInfo order,StateType state){
		String hql = "update OrderInfo o set o.state=:state,finishDate=:finishDate where o.orderId=:orderId and o.orderType=:orderType";
		Map<String, Object> map = new HashMap();
		map.put("orderId", order.getOrderId());
		map.put("state", state);
		map.put("orderType", order.getOrderType());
		map.put("finishDate", new Date());
		this.getBaseDao().batchExecute(hql,map);
	}
	
	public List<OrderInfo> getUnsendOrder(OrderType type){
		String hql = "from OrderInfo o where o.orderType = :orderType and o.state=0";
		Map<String, OrderType> map = new HashMap();
		map.put("orderType", type);
		return this.getBaseDao().find(hql,map);
	}
	
	public AppointmentVo getAppointmentVo(String orderId){
		AppointmentVo vo = new AppointmentVo();
		String hql = "from ReserveMain r where r.rsvNo=:orderId";
		Map<String, String> map = new HashMap();
		map.put("orderId", orderId);
		vo.setReserveMain((ReserveMain)reserveMainDao.findUnique(hql, map));
		String hql2 = "from ReserveDetail r where r.rsvNo=:orderId";
		vo.setReserveDetails(reserveDetailDao.getReserveDetails(hql2, map));
		return vo;
	}
	
	public AppointmenResult getAppointmenResult(String orderId){
		AppointmenResult vo = new AppointmenResult();
		String hql = "from ReserveMain r where r.rsvNo=:orderId";
		Map<String, String> map = new HashMap();
		map.put("orderId", orderId);
		ReserveMain reserveMain =(ReserveMain)reserveMainDao.findUnique(hql, map);
		vo.setOrderId(orderId);
		vo.setCheckRemark(reserveMain.getCheckRemark());
		vo.setState(reserveMain.getState());
		vo.setBankId(reserveMain.getBankId());
		return vo;
	}
	
	public boolean saveAppointmentVo(AppointmentVo appointment){
		if(appointment == null) return false;
		reserveMainDao.save(appointment.getReserveMain());
		for(ReserveDetail o:appointment.getReserveDetails()){
			if(null != o.getFid()){
				o.setFid(null);
			}
		}
		reserveDetailDao.saveLosts(appointment.getReserveDetails());
		return true;
	}
	
	public boolean savePaymentVo(PaymentVo payment){
		if(payment == null)return false;
		payOrderDao.save(payment.getPayOrder());
//		for(PayOrderDetail o:payment.getPayOrderDetails()){
//			o.setDid(null);
//		}
		payOrderDetailDao.saveLosts(payment.getPayOrderDetails());
//		for(PayBag o:payment.getPayBags()){
//			o.setId(null);
//		}
		payBagDao.saveLosts(payment.getPayBags());
//		for(PayBundle o:payment.getPayBundles()){
//			o.setId(null);
//		}
		payBundleDao.saveLosts(payment.getPayBundles());
		return true;
	}
	
	public boolean saveQryApply(QryApply qryApply){
		if(qryApply == null)return false;
		qryApplyDao.save(qryApply);
		return true;
	}
}
