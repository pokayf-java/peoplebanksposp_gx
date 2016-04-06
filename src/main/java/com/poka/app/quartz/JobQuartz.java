package com.poka.app.quartz;

import org.springframework.beans.factory.annotation.Autowired;

import com.poka.app.anno.bussiness.AppointmentBusiness;
import com.poka.app.anno.bussiness.MonRuleBusiness;
import com.poka.app.util.ConstantUtil;


public class JobQuartz  {
	
	AppointmentBusiness appointmentBussiness;
	MonRuleBusiness monRuleBussiness;
	
	@Autowired
	public void setAppointmentBussiness(AppointmentBusiness appointmentBussiness) {
		this.appointmentBussiness = appointmentBussiness;
	}
	@Autowired
	public void setMonRuleBussiness(MonRuleBusiness monRuleBussiness) {
		this.monRuleBussiness = monRuleBussiness;
	}

	public void work() {
		
		if(ConstantUtil.appointmentFlag.equals("Enabled")){
			appointmentBussiness.handAppointment();
		}
		
		if(ConstantUtil.monRuleFlag.equals("Enabled")){
			monRuleBussiness.sendMonRuleData();
		}
		
	}

}
