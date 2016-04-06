package com.poka.app.anno.base.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.PerInfo;

@Service
public class PerInfoService extends BaseService<PerInfo, String> {

	public int getPerinfoCount(){
		String hql = "select count(*)  from PerInfo";
		return ((Integer)this.baseDao.findUnique(hql)).intValue();
	}
	
	public List<PerInfo> getPerinfoList(int firstResult,int maxResults){
		String hql = "from PerInfo";
		Query query = createQuery(hql);
		query.setMaxResults(maxResults);
		query.setFirstResult(firstResult);
		return (List<PerInfo>)query.list();
	}
	
	public PerInfo getPerinfo(String pk){
		String hql = "from PerInfo p where p.bankno = :percode";
		Query query = createQuery(hql);
		query.setParameter("percode", pk);
		return (PerInfo)query.uniqueResult();
	}
}
