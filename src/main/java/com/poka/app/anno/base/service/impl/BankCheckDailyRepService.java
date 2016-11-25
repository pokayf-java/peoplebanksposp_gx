package com.poka.app.anno.base.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepPK;

@Service
public class BankCheckDailyRepService extends BaseService<BankCheckDailyRep, BankCheckDailyRepPK> {
	
	public List<BankCheckDailyRep> getBankCheckDailyRep(){
		String hql = " FROM BankCheckDailyRep ";
		Query query = createQuery(hql);
		return (List<BankCheckDailyRep>)query.list();
	}

}
