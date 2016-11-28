package com.poka.app.anno.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.PayBag;
import com.poka.app.anno.enity.PayOrder;
import com.poka.app.anno.enity.PayOrderPK;
import com.poka.app.anno.enity.ScanOrder;
import com.poka.app.anno.enity.StockBag;

@Service
public class PayOrderService extends BaseService<PayOrder, PayOrderPK> {

	/**
	 * 获取扫描订单号
	 * 
	 * @param machId
	 * @param unitId
	 * @return
	 */
	public String getOrderId(String machId, String unitId) {
		String hql = "from ScanOrder where machId =:machId and unitId =:unitId";
		Map<String, String> map = new HashMap<String, String>();
		map.put("machId", machId);
		map.put("unitId", unitId);
		ScanOrder scanOrder = (ScanOrder) baseDao.findUnique(hql, map);
		String orderId = "";
		if (null != scanOrder) {
			orderId = scanOrder.getOrderId();
		}
		return orderId;
	}

	/**
	 * 获取订单类型
	 * 
	 * @param provId
	 * @param unitId
	 * @param orderId
	 * @return
	 */
	public PayOrder getOrderType(String provId, String unitId, String orderId) {

		String hql = "from PayOrder where provId =:provId and (unitId =:unitId or fromUnitId =:fromUnitId) and orderId =:orderId ";
		Map<String, String> map = new HashMap<String, String>();
		map.put("provId", provId);
		map.put("unitId", unitId);
		map.put("fromUnitId", unitId);
		map.put("orderId", orderId);
		PayOrder payOrder = (PayOrder) baseDao.findUnique(hql, map);

		return payOrder;
	}

	/**
	 * 查询当前标签是否已经扫描过了
	 * 
	 * @param orderId
	 * @param tagId
	 * @param provId
	 * @param unitId
	 * @param unitFlag
	 * @return
	 */
	public boolean getBagsCheckFlag(String orderId, String tagId, String provId, String unitId, boolean unitFlag) {
		String hql = "from BagsCheck where orderId=:orderId and bagCode=:bagCode and provId =:provId";
		if (unitFlag) {
			hql = hql+ " and unitId =:unitId";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderId", orderId);
		map.put("bagCode", tagId);
		map.put("provId", provId);
		if (unitFlag) {
			map.put("unitId", unitId);
		}
		if (null != baseDao.findUnique(hql, map)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询劵种，劵别
	 * 
	 * @param tagId
	 * @param orderId
	 * @param tablesName
	 * @param unitFlag
	 * @param orderFlag
	 * @return
	 */
	public StockBag getCurrencyIdKindForStock(String tagId, String provId, String unitId) {

		String hql = "from StockBag where bagCode =:bagCode and provId =:provId and unitId =:unitId";
		Map<String, String> map = new HashMap<String, String>();
		map.put("bagCode", tagId);
		map.put("provId", provId);
		map.put("unitId", unitId);
		return (StockBag) baseDao.findUnique(hql, map);

	}
	/**
	 * 查询劵种，劵别
	 * 
	 * @param tagId
	 * @param orderId
	 * @param tablesName
	 * @param unitFlag
	 * @param orderFlag
	 * @return
	 */
	public PayBag getCurrencyIdKindForBags(String tagId, String provId,String orderId, String unitId, boolean orderFlag,boolean unitFlag) {
		
		String hql = "from PayBag where bagCode =:bagCode and provId =:provId and orderId =:orderId";
		Map<String, String> map = new HashMap<String, String>();
		map.put("bagCode", tagId);
		map.put("provId", provId);
		map.put("orderId", orderId);
		if(unitFlag){
			hql = hql +" and unitId =:unitId ";
			map.put("unitId", unitId);
		}
		if(orderFlag){
			hql = hql +" and bagState = 0";
		}
		return (PayBag) baseDao.findUnique(hql, map);
		
	}

	/**
	 * 查看当前扫描到的劵种劵别是否符合当前订单
	 * 
	 * @param conn
	 * @param orderId
	 * @param tagId
	 * @param currencyId
	 * @param currencyKind
	 * @param unitFlag
	 * @return
	 */
	public boolean getOrderDetailFlag(String orderId, String currencyId, Integer currencyKind, String provId,
			String unitId, boolean unitFlag) {

		String hql = "from PayOrderDetail where orderId=:orderId and currencyId=:currencyId and currencyKind=:currencyKind and provId =:provId";
		Map<String,Object> map = new HashMap<String, Object>();
		if (unitFlag) {
			hql = hql + " and unitId =:unitId ";
		}
		map.put("orderId", orderId);
		map.put("currencyId", currencyId);
		map.put("currencyKind", currencyKind);
		map.put("provId", provId);
		if (unitFlag) {
			map.put("unitId", unitId);
		}
		List queryList = baseDao.createQuery(hql, map).list();
		if (null !=queryList && queryList.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	

}
