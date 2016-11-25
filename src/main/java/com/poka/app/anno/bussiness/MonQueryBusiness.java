package com.poka.app.anno.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankInfoService;
import com.poka.app.anno.enity.BankInfo;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.util.CxfUtil;

@Component
public class MonQueryBusiness {
	Logger logger = Logger.getLogger(MonQueryBusiness.class);
	private BankInfoService bankInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	@Autowired
	@Qualifier("bankInfoService")
	public void setBankInfoService(BankInfoService bankInfoService) {
		this.bankInfoService = bankInfoService;
	}

	/**
	 * 查询冠字号信息
	 */
	public List<MoneyDataInfo> selectGZHData(String mon) {
		List<BankInfo> bankInfoList = bankInfoService.getBankInfoList();
		List<MoneyDataInfo> listAll = new ArrayList<MoneyDataInfo>();
		if (null != bankInfoList && bankInfoList.size() > 0) {
			for (BankInfo bankInfo : bankInfoList) {
				String bankIp = bankInfo.getIpAddr().trim();
				String tempPort = null ;
				String bankNo = bankInfo.getBankNo();
				String bankName = bankInfo.getBankName();
				if (null != bankIp && !bankIp.equals("")) {
					if (null != bankInfo.getIpport() && !"".equals(bankInfo.getIpport())) {
						tempPort = bankInfo.getIpport().trim();
					} else {
						tempPort = cxfUtil.getPort();
					} 
					ICBPospSW service = cxfUtil.getCxfClient(ICBPospSW.class,
							cxfUtil.getUrl(bankIp,tempPort));
					cxfUtil.recieveTimeOutWrapper(service);
					List<MoneyDataInfo> result = new ArrayList<MoneyDataInfo>();
					try {
						result = service.selectGZHData(mon);
					} catch (Exception e) {
						logger.info("银行号：" + bankNo+" ip:" + bankIp+" 连接服务器失败!");
						MoneyDataInfo moneyDataInfo = new MoneyDataInfo();
						moneyDataInfo.setLocalBankName(bankName);
						moneyDataInfo.setCauseInfo(1);
						listAll.add(moneyDataInfo);
						continue;
					}
					
					if (null != result && result.size() > 0) {
						for (MoneyDataInfo moneyDataInfo : result) {
							moneyDataInfo.setCountNum(result.size());
							moneyDataInfo.setLocalBankName(bankName);
							listAll.add(moneyDataInfo);
						}
						logger.info("银行号：" + bankNo+" ip:" + bankIp + " 共计: " + result.size());
					} else {
						MoneyDataInfo moneyDataInfo = new MoneyDataInfo();
						moneyDataInfo.setLocalBankName(bankName);
						moneyDataInfo.setCountNum(0);
						moneyDataInfo.setCauseInfo(3);
						listAll.add(moneyDataInfo);
						logger.info("银行号：" + bankNo+" ip:" + bankIp + " 共计: 0");
					}
				} else {
					logger.info("银行号：" + bankNo + " 未配置前置机IP");
				}
			}
			return listAll;
		} else {
			return null;
		}
	}
}
