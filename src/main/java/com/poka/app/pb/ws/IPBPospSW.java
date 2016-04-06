package com.poka.app.pb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.BagInfo;
import com.poka.app.anno.enity.BankCheckDailyRepBak;
import com.poka.app.anno.enity.NetCheckDailyRepBak;
import com.poka.app.anno.enity.QryApply;
import com.poka.app.vo.AppointmentVo;
import com.poka.app.vo.PaymentVo;

@WebService
public interface IPBPospSW {
	
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
	public boolean getPerInfoData(@WebParam(name = "listData")String  listData);
	
	@WebMethod(operationName = "getBanchInfoData")
	@WebResult(name = "result")
	public boolean getBanchInfoData(@WebParam(name = "listData")String  listData);
	
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
}
