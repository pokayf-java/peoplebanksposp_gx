package com.poka.app.anno.bussiness;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankCheckDailyRepBakService;
import com.poka.app.anno.enity.BankCheckDailyRepBak;

@Component
public class BankCheckDailyRepBakBusiness {
	Logger logger = Logger.getLogger(BankCheckDailyRepBakBusiness.class);
	private BankCheckDailyRepBakService bankCheckDailyRepBakService;

	@Autowired 
	@Qualifier("bankCheckDailyRepBakService")
	public void setBankCheckDailyRepService(BankCheckDailyRepBakService bankCheckDailyRepBakService) {
		this.bankCheckDailyRepBakService = bankCheckDailyRepBakService;
	}
	
	
	public boolean getBankCheckDailyRepBak(List<BankCheckDailyRepBak> bankListData){
		String bankNo = "";
		if(null!=bankListData&&bankListData.size()>0){
			for(BankCheckDailyRepBak bankCheckDailyRepBak:bankListData){
				bankNo = bankCheckDailyRepBak.getBankNo();
				bankCheckDailyRepBakService.save(bankCheckDailyRepBak);
			}
		}
		logger.info("银行("+bankNo+")日结数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		return true;
	}
}
