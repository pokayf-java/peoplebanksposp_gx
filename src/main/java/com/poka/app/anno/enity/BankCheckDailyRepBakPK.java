package com.poka.app.anno.enity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class BankCheckDailyRepBakPK implements Serializable {

	private String bankNo;
	private String checkDate;
	private BigDecimal monVal;		
	private BigInteger records;		
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BankCheckDailyRepBakPK) {
			BankCheckDailyRepBakPK pk = (BankCheckDailyRepBakPK) obj;
			if (pk.getBankNo().equals(this.bankNo) && pk.getCheckDate().equals(this.checkDate)
					&&pk.getMonVal().equals(this.monVal)&&pk.getRecords().equals(this.records)) {
				return true;
			}

		}
		return false;
	}

	@Override
	public int hashCode() {
		return (this.getBankNo() + this.getCheckDate()+this.getMonVal()+this.getRecords()).hashCode();
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public BigDecimal getMonVal() {
		return monVal;
	}

	public void setMonVal(BigDecimal monVal) {
		this.monVal = monVal;
	}

	public BigInteger getRecords() {
		return records;
	}

	public void setRecords(BigInteger records) {
		this.records = records;
	}
}
