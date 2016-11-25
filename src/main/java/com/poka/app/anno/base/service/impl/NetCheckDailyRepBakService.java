package com.poka.app.anno.base.service.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.NetCheckDailyRep;
import com.poka.app.anno.enity.NetCheckDailyRepBak;
import com.poka.app.anno.enity.NetCheckDailyRepBakPK;

@Service
public class NetCheckDailyRepBakService extends BaseService<NetCheckDailyRepBak, NetCheckDailyRepBakPK> {
	
	public List<NetCheckDailyRep> getNetCheckDailyRep(){
		String hql = " FROM NetCheckDailyRepBak ";
		Query query = createQuery(hql);
		return (List<NetCheckDailyRep>)query.list();
	}

}
