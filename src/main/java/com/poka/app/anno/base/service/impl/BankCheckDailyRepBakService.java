package com.poka.app.anno.base.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.BankCheckDailyRep;
import com.poka.app.anno.enity.BankCheckDailyRepBak;
import com.poka.app.anno.enity.BankCheckDailyRepBakPK;

@Service
public class BankCheckDailyRepBakService extends BaseService<BankCheckDailyRepBak, BankCheckDailyRepBakPK> {
	
	public List<BankCheckDailyRep> getBankCheckDailyRep(){
		String hql = " FROM BankCheckDailyRepBak ";
		Query query = createQuery(hql);
		return (List<BankCheckDailyRep>)query.list();
	}

}
