package com.poka.app.anno.base.service.impl;


import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BagsCheck;


@Service
public class BagsCheckService extends BaseService<BagsCheck,Integer> {
	
	
	/**
	 * 将符合要求标签插入到bagscheck表中
	 * 
	 * @param conn
	 * @param orderId
	 * @param tagId
	 * @param currencyId
	 * @param currencyKind
	 */
	public boolean insertBagsCheckFlag(String orderId, String tagId, String currencyId, Integer currencyKind, String provId, String unitId,
			boolean unitflag, String fromUnitId) {
		
		BagsCheck bagsCheck = new BagsCheck();
		bagsCheck.setOrderId(orderId);
		bagsCheck.setBagCode(tagId);
		bagsCheck.setCurrencyId(currencyId);
		bagsCheck.setCurrencyKind(currencyKind);
		bagsCheck.setProvId(provId);
		if(unitflag){
			bagsCheck.setUnitId(fromUnitId);
		}else{
			bagsCheck.setUnitId(unitId);
		}
		this.baseDao.save(bagsCheck);
		return true;
	}
}
