package com.poka.app.util;

import com.poka.app.util.ArgsConfigUtil;
import com.poka.app.util.ConstantUtil;
/**
 * 初始化常量
 * @author Administrator
 *
 */
public class InitConstant {
	private static ArgsConfigUtil argsConfigUtil = null;
	static {
		if (argsConfigUtil == null) {
			argsConfigUtil = new ArgsConfigUtil();
			ConstantUtil.appointmentPeopFlag = argsConfigUtil.getAppointmentPeopFlag();
			ConstantUtil.appointmentCommFlag = argsConfigUtil.getAppointmentCommFlag();
			ConstantUtil.monRuleFlag = argsConfigUtil.getMonRuleFlag();
			ConstantUtil.bankAndNetRepFlag = argsConfigUtil.getBankAndNetRepFlag();
			ConstantUtil.branchInfoFlag = argsConfigUtil.getBranchInfoFlag();
			ConstantUtil.perInfoFlag = argsConfigUtil.getPerInfoFlag();
			ConstantUtil.paymentFlag = argsConfigUtil.getPaymentFlag();
			ConstantUtil.qryApplyFlag = argsConfigUtil.getQryApplyFlag();
		}
	}
}
