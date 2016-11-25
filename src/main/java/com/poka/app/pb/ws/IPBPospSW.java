package com.poka.app.pb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRepBak;
import com.poka.app.anno.enity.BranchInfo;
import com.poka.app.anno.enity.NetCheckDailyRepBak;
import com.poka.app.anno.enity.PerInfo;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@WebService
public interface IPBPospSW {
	/*获取传输指令*/
	@WebMethod(operationName = "getTransferFlag")
	@WebResult(name = "result")
	public boolean getTransferFlag(@WebParam(name = "transferInfo") String transferInfo);

	@WebMethod(operationName = "makeAppointmen")
	@WebResult(name = "result")
	public boolean makeAppointmen(
			@WebParam(name = "appointment") AppointmentVo appointment);
	
	@WebMethod(operationName = "makePayment")
	@WebResult(name = "result")
	public boolean makePayment(@WebParam(name = "payment")PaymentVo payment);
	
	@WebMethod(operationName = "makeQryApply")
	@WebResult(name = "result")
	public boolean makeQryApply(@WebParam(name = "qryApply")QryApply qryApply);
	
	@WebMethod(operationName = "getPerInfoData")
	@WebResult(name = "result")
	public boolean getPerInfoData(@WebParam(name = "perInfoList")List<PerInfo> perInfoList);
	
	@WebMethod(operationName = "getBranchInfoData")
	@WebResult(name = "result")
	public boolean getBranchInfoData(@WebParam(name = "branchInfoList")List<BranchInfo> branchiInfoList);
	
	@WebMethod(operationName = "sendNetCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendNetCheckDailyRepBak(@WebParam(name = "netCheckDailyRepList")List<NetCheckDailyRepBak>  netCheckDailyRepList);
	
	@WebMethod(operationName = "sendBankCheckDailyRep")
	@WebResult(name = "result")
	public boolean sendBankCheckDailyRepBak(@WebParam(name = "bankCheckDailyRepList")List<BankCheckDailyRepBak>  bankCheckDailyRepList);
	
	/*横向调拨*/
	@WebMethod(operationName = "sendBagInfo")
	@WebResult(name = "result")
	public boolean sendBagInfo(@WebParam(name = "bagInfoList") List<BagInfo> bagInfoList);
	
	/*获取RFID标签*/
	@WebMethod(operationName = "getTagId")
	@WebResult(name="result")	
	public String getTagId(@WebParam(name = "tagId")String tagId);
	
	/* 测试与服务器的连接*/
	@WebMethod(operationName = "getConnectStatus")
	@WebResult(name="result")	
	public boolean getConnectStatus(@WebParam(name = "connectStr")String connectStr);
	
	
}
