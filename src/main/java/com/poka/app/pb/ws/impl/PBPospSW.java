package com.poka.app.pb.ws.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBusiness;
import com.poka.app.anno.bussiness.BagInfoBusiness;
import com.poka.app.anno.bussiness.BankCheckDailyRepBakBusiness;
import com.poka.app.anno.bussiness.BankCheckDailyRepBusiness;
import com.poka.app.anno.bussiness.NetCheckDailyRepBakBusiness;
import com.poka.app.anno.bussiness.NetCheckDailyRepBusiness;
import com.poka.app.anno.bussiness.PaymentBusiness;
import com.poka.app.anno.bussiness.PerInfoAndBranchBusiness;
import com.poka.app.anno.bussiness.QryApplyBusiness;
import com.poka.app.anno.bussiness.RfidScannerBusiness;
import com.poka.app.anno.bussiness.TransferFlagBusiness;
import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRepBak;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.NetCheckDailyRepBak;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.pb.ws.IPBPospSW;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@WebService(endpointInterface = "com.poka.app.pb.ws.IPBPospSW")
public class PBPospSW implements IPBPospSW {

	private AppointmentBusiness appointmentBussiness;
	private PaymentBusiness paymentBussiness;
	private QryApplyBusiness qryApplyBussiness;
	private PerInfoAndBranchBusiness perInfoAndBranchBussiness;
	private BankCheckDailyRepBusiness bankCheckDailyRepBussiness;
	private BankCheckDailyRepBakBusiness bankCheckDailyRepBakBussiness;
	private NetCheckDailyRepBusiness netCheckDailyRepBussiness;
	private NetCheckDailyRepBakBusiness netCheckDailyRepBakBussiness;
	private BagInfoBusiness bagInfoBusiness;
	private RfidScannerBusiness rfidScannerBusiness;
	private TransferFlagBusiness transferFlagBusiness;

	@Autowired
	public void setRfidScannerBusiness(RfidScannerBusiness rfidScannerBusiness) {
		this.rfidScannerBusiness = rfidScannerBusiness;
	}
	
	@Autowired
	public void setTransferFlagBusiness(TransferFlagBusiness transferFlagBusiness) {
		this.transferFlagBusiness = transferFlagBusiness;
	}
	
	@Autowired
	public void setBankCheckDailyRepBakBussiness(BankCheckDailyRepBakBusiness bankCheckDailyRepBakBussiness) {
		this.bankCheckDailyRepBakBussiness = bankCheckDailyRepBakBussiness;
	}
	
	@Autowired
	public void setBagInfoBusiness(BagInfoBusiness bagInfoBusiness) {
		this.bagInfoBusiness = bagInfoBusiness;
	}

	
	@Autowired
	public void setNetCheckDailyRepBakBussiness(NetCheckDailyRepBakBusiness netCheckDailyRepBakBussiness) {
		this.netCheckDailyRepBakBussiness = netCheckDailyRepBakBussiness;
	}
	
	@Autowired
	public void setBankCheckDailyRepBussiness(BankCheckDailyRepBusiness bankCheckDailyRepBussiness) {
		this.bankCheckDailyRepBussiness = bankCheckDailyRepBussiness;
	}
	
	
	@Autowired
	public void setNetCheckDailyRepBussiness(NetCheckDailyRepBusiness netCheckDailyRepBussiness) {
		this.netCheckDailyRepBussiness = netCheckDailyRepBussiness;
	}
	
	@Autowired
	public void setPerInfoAndBranchBussiness(PerInfoAndBranchBusiness perInfoAndBranchBussiness) {
		this.perInfoAndBranchBussiness = perInfoAndBranchBussiness;
	}

	@Autowired
	public void setPaymentBussiness(PaymentBusiness paymentBussiness) {
		this.paymentBussiness = paymentBussiness;
	}

	@Autowired
	public void setQryApplyBussiness(QryApplyBusiness qryApplyBussiness) {
		this.qryApplyBussiness = qryApplyBussiness;
	}

	@Autowired
	public void setAppointmentBussiness(AppointmentBusiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}

	/*
	 * 预约请求处理
	 * 
	 * @see com.poka.app.pb.ws.IPBPospSW#makeAppointmen(com.poka.app.vo.
	 * AppointmentVo)
	 */
	@Override
	public boolean makeAppointmen(AppointmentVo appointment) {

		return appointmentBussiness.makeAppointment(appointment);
	}

	/*
	 * 交款处理
	 * 
	 * @see com.poka.app.pb.ws.IPBPospSW#makePayment(com.poka.app.vo.PaymentVo)
	 */
	@Override
	public boolean makePayment(PaymentVo payment) {
		return paymentBussiness.makePayment(payment);
	}

	@Override
	public boolean makeQryApply(QryApply qryApply) {
		return qryApplyBussiness.makeQryApply(qryApply);
	}

	@Override
	public boolean getPerInfoData(List<PerInfo> perInfoList) {
		return perInfoAndBranchBussiness.updatePerInfo(perInfoList);
	}

	@Override
	public boolean getBranchInfoData(List<BranchInfo> branchInfoList) {
		return perInfoAndBranchBussiness.updateBranchInfo(branchInfoList);
	}

	@Override
	public boolean sendBankCheckDailyRepBak(List<BankCheckDailyRepBak> bankListData) {
		// TODO Auto-generated method stub
		return bankCheckDailyRepBakBussiness.getBankCheckDailyRepBak(bankListData);
	}

	@Override
	public boolean sendNetCheckDailyRepBak(List<NetCheckDailyRepBak> netCheckDailyRepList) {
		// TODO Auto-generated method stub
		return netCheckDailyRepBakBussiness.getNetCheckDailyRepBak(netCheckDailyRepList);
	}


	@Override
	public boolean sendBagInfo(List<BagInfo> bagInfoList) {
		// TODO Auto-generated method stub
		return bagInfoBusiness.updateBagInfo(bagInfoList);
	}

	@Override
	public String getTagId(String tagId) {
		return rfidScannerBusiness.doRfidScanner(tagId);
	}

	@Override
	public boolean getConnectStatus(String connectStr) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean getTransferFlag(String transferInfo) {
		// TODO Auto-generated method stub
		return transferFlagBusiness.getTransferInfo(transferInfo);
	}

}
