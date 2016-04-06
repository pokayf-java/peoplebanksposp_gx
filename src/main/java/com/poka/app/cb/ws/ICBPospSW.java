package com.poka.app.cb.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.poka.app.anno.enity.MonRule;
import com.poka.app.vo.AppointmenResult;

@WebService
public interface ICBPospSW {
	@WebMethod(operationName = "handleAppointmen")
	@WebResult(name = "result")
	public boolean handleAppointmen(@WebParam(name = "appointment") AppointmenResult appointment);

	@WebMethod(operationName = "sendMonRuleData")
	@WebResult(name = "result")
	public boolean sendMonRuleData(@WebParam(name = "monRuleList") List<MonRule> monRuleList);

}
