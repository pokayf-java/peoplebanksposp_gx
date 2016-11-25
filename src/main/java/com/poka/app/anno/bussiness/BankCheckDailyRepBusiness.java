package com.poka.app.anno.bussiness;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankCheckDailyRepService;
import com.poka.app.anno.enity.BankCheckDailyRep;

@Component
public class BankCheckDailyRepBusiness {
	Logger logger = Logger.getLogger(BankCheckDailyRepBusiness.class);
	private BankCheckDailyRepService bankCheckDailyRepService;

	@Autowired 
	@Qualifier("bankCheckDailyRepService")
	public void setBankCheckDailyRepService(BankCheckDailyRepService bankCheckDailyRepService) {
		this.bankCheckDailyRepService = bankCheckDailyRepService;
	}
	
	
	public boolean getBankCheckDailyRep(List<BankCheckDailyRep> bankListData){
		String bankNo = "";
		if(null!=bankListData&&bankListData.size()>0){
			for(BankCheckDailyRep bankCheckDailyRep:bankListData){
				bankNo = bankCheckDailyRep.getBankNo();
				bankCheckDailyRepService.save(bankCheckDailyRep);
			}
		}
		
		logger.info("银行("+bankNo+")日结数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		return true;
	}
}
