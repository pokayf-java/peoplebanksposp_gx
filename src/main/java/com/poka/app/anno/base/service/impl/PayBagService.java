package com.poka.app.anno.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poka.app.anno.enity.PayBag;
import com.poka.app.anno.enity.PayBagPK;

@Service
public class PayBagService extends BaseService<PayBag,PayBagPK> {

	
	
	/**
	 * 查询扫描到的标签是否在bags表中。
	 * @param tagId
	 * @return
	 */
	public PayBag selectBagsInfo(String orderId, String tagId){
		String hql = "from PayBag where bagCode =:bagCode and orderId =:orderId";
		Map<String, String> map = new HashMap<String, String>();
		map.put("bagCode", tagId);
		map.put("orderId", orderId);
		PayBag payBag = (PayBag)baseDao.findUnique(hql, map);
		return payBag;
	}
	
	/**
	 * 更新bags表的状态
	 * @param orderId
	 * @param tagId
	 * @return
	 */
	public boolean updateBags(String orderId, String tagId){
		String sql = "update Bags set state=1,scanId='RF', Msg='Checked' where orderId='"+orderId+ "' and bagCode='"+tagId+"'and state=0 ";
		int temp = baseDao.excuteBySql(sql);
		if(temp>0){
			return true;
		}else{
			return false;
		}
		
	}
}
