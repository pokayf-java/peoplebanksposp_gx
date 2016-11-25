package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poka.app.anno.base.dao.impl.MoneyDataDao;
import com.poka.app.anno.base.service.IMoneyDataService;
import com.poka.app.anno.enity.MoneyDataInfo;

@Service
public class MoneyDataService implements IMoneyDataService {

	private MoneyDataDao moneyDataDao;
	
	@Autowired
	public void setMoneyDataDao(MoneyDataDao moneyDataDao) {
		this.moneyDataDao = moneyDataDao;
	}

	@Override
	public List<MoneyDataInfo> findMoneyDataList(String mon) throws Exception {
		return this.moneyDataDao.findMoneyDataList(mon);
	}

	@Override
	public List<MoneyDataInfo> findMoneyDataListByLike(String mon)
			throws Exception {
		return this.moneyDataDao.findMoneyDataListByLike(mon);
	}

}
