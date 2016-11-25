package com.poka.app.anno.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.poka.app.anno.base.service.impl.BankInfoService;
import com.poka.app.anno.base.service.impl.MonRuleService;
import com.poka.app.anno.enity.BankInfo;
import com.poka.app.anno.enity.MonRule;
import com.poka.app.anno.enity.MoneyDataInfo;
import com.poka.app.cb.ws.ICBPospSW;
import com.poka.app.util.CxfUtil;

@Component
public class MonRuleBusiness {
	Logger logger = Logger.getLogger(MonRuleBusiness.class);
	private MonRuleService monRuleService;
	private BankInfoService bankInfoService;

	private CxfUtil cxfUtil;

	@Autowired
	public void setCxfUtil(CxfUtil cxfUtil) {
		this.cxfUtil = cxfUtil;
	}

	@Autowired
	@Qualifier("monRuleService")
	public void setMonRuleService(MonRuleService monRuleService) {
		this.monRuleService = monRuleService;
	}

	@Autowired
	@Qualifier("bankInfoService")
	public void setBankInfoService(BankInfoService bankInfoService) {
		this.bankInfoService = bankInfoService;
	}

	/**
	 * 可疑币更新
	 */
	public void sendMonRuleData() {

		List<BankInfo> bankInfoList = bankInfoService.getBankInfoList();
		if (bankInfoList.size() > 0) {
			for (int i = 0; i < bankInfoList.size(); i++) {

				BankInfo bankInfo = bankInfoList.get(i);
				String bankIp = bankInfo.getIpAddr().trim();

				ICBPospSW service = cxfUtil.getCxfClient(ICBPospSW.class, cxfUtil.getUrl(bankIp, cxfUtil.getPort()));
				cxfUtil.recieveTimeOutWrapper(service);

				boolean result = Boolean.FALSE;
				List<MonRule> monRuleList = monRuleService.getMonRuleList();

				if (null != monRuleList && monRuleList.size() > 0) {
					try {
						result = service.sendMonRuleData(monRuleList);
					} catch (Exception ex) {
						logger.info("连接服务器失败!");
					}
					if (result) {
						logger.info("ip:" + bankIp + "可疑币数据同步成功");
						logger.info("总计:" + monRuleList.size() + "条");
					} else {
						logger.info("ip:" + bankIp + "可疑币数据同步失败");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}

	}
}
