package com.poka.app.vo;

import java.io.Serializable;
import java.util.List;

import com.poka.app.anno.enity.ReserveDetail;
import com.poka.app.anno.enity.ReserveMain;

public class AppointmentVo implements Serializable {
	private ReserveMain reserveMain;
	private List<ReserveDetail> reserveDetails;
	
	
	public AppointmentVo(){}
	@Override
	public String toString() {
		return "AppointmentVo [reserveMain=" + reserveMain
				+ ", reserveDetails=" + reserveDetails + "]";
	}
	public ReserveMain getReserveMain() {
		return reserveMain;
	}
	public void setReserveMain(ReserveMain reserveMain) {
		this.reserveMain = reserveMain;
	}
	public List<ReserveDetail> getReserveDetails() {
		return reserveDetails;
	}
	public void setReserveDetails(List<ReserveDetail> reserveDetails) {
		this.reserveDetails = reserveDetails;
	}
	
}
