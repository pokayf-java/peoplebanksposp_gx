package com.poka.app.anno.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.NetCheckDailyRepBakService;
import com.poka.app.anno.enity.NetCheckDailyRepBak;

@Component
public class NetCheckDailyRepBakBusiness {
	Logger logger = Logger.getLogger(NetCheckDailyRepBakBusiness.class);
	private NetCheckDailyRepBakService netCheckDailyRepBakService;

	@Autowired
	@Qualifier("netCheckDailyRepBakService")
	public void setBankCheckDailyRepService(NetCheckDailyRepBakService netCheckDailyRepBakService) {
		this.netCheckDailyRepBakService = netCheckDailyRepBakService;
	}

	public boolean getNetCheckDailyRepBak(final List<NetCheckDailyRepBak> netListData) {
		
		String netNo = "";
		if (null != netListData && netListData.size() > 0) {
//			final int netListSize = netListData.size();
//			final int startSize = netListSize/2;
//			Thread thread1 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for(int i=0;i<startSize;i++){
//						NetCheckDailyRep netCheckDailyRep  = netListData.get(i);
//						netCheckDailyRepService.save(netCheckDailyRep);
//					}
//					
//				}
//			});
//			Thread thread2 = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for (int i = startSize; i < netListSize; i++) {
//						NetCheckDailyRep netCheckDailyRep  = netListData.get(i);
//						netCheckDailyRepService.save(netCheckDailyRep);
//					}
//					
//				}
//			});
//			thread1.start();
//			thread2.start();
			for (NetCheckDailyRepBak netCheckDailyRepBak : netListData) {
				netNo = netCheckDailyRepBak.getNetNo();
				netCheckDailyRepBakService.save(netCheckDailyRepBak);
			}
		}
		logger.info("网点("+netNo+")日结数据同步成功！("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+")");
		return true;
	}
}
