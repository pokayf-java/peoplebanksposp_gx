package com.poka.app.anno.base.service;

import java.util.List;

import com.poka.app.anno.enity.MoneyDataInfo;



public interface IMoneyDataService {

	/**
	 * get money data by mon
	 * @param mon ：money data code
	 * @return	： money data list
	 * @throws Exception
	 */
	public List<MoneyDataInfo> findMoneyDataList( String mon ) throws Exception;
	
	public List<MoneyDataInfo> findMoneyDataListByLike( String mon ) throws Exception;
/*
	public List<MoneyDataInfo> findMoneyDataListPage(String page, String rows, String dealNo) throws Exception;

	public int countResult(String dealNo) throws Exception;

	public int getCount() throws Exception;
	*/
	
}
