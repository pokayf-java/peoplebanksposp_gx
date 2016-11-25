package com.poka.app.anno.base.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepPK;

@Service
public class NetCheckDailyRepService extends BaseService<NetCheckDailyRep, NetCheckDailyRepPK> {
	
	public List<NetCheckDailyRep> getNetCheckDailyRep(){
		String hql = " FROM NetCheckDailyRep ";
		Query query = createQuery(hql);
		return (List<NetCheckDailyRep>)query.list();
	}

}
