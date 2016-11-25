package com.poka.app.anno.bussiness;

import org.springframework.stereotype.Component;

import com.poka.app.util.ConstantUtil;

@Component
public class TransferFlagBusiness {

	public boolean getTransferInfo(String transferInfo) {
		if (transferInfo.equals("branchInfo")) {
			if (ConstantUtil.branchInfoFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("perInfo")) {
			if (ConstantUtil.perInfoFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("bankAndNetRep")) {
			if (ConstantUtil.bankAndNetRepFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("appointment")) {
			if (ConstantUtil.appointmentCommFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("payment")) {
			if (ConstantUtil.paymentFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("qryApply")) {
			if (ConstantUtil.qryApplyFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		if (transferInfo.equals("bagInfo")) {
			if (ConstantUtil.bagInfoFlag.trim().equals("Enabled")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
