package com.poka.app.anno.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "BAGINFO")
@IdClass(value = BagInfoPK.class)
public class BagInfo implements Serializable {
	
	private String bagCode;
	private String bankId;
	private Date bagDate;
	private String bagBagId;
	private String bagCheckId;
	private String bundleCode;
	private Date bundleDate;
	private String writeStatus;
	private String putStatus;
	
	
	@Column(name = "bagCode",length = 20)
	public String getBagCode() {
		return bagCode;
	}
	public void setBagCode(String bagCode) {
		this.bagCode = bagCode;
	}
	
	@Id
	@Column(name = "bankId",length = 20)
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Column(name = "bagDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBagDate() {
		return bagDate;
	}
	public void setBagDate(Date bagDate) {
		this.bagDate = bagDate;
	}
	
	@Column(name = "bagBagId",length = 20)
	public String getBagBagId() {
		return bagBagId;
	}
	public void setBagBagId(String bagBagId) {
		this.bagBagId = bagBagId;
	}
	
	@Column(name = "bagCheckId",length = 20)
	public String getBagCheckId() {
		return bagCheckId;
	}
	public void setBagCheckId(String bagCheckId) {
		this.bagCheckId = bagCheckId;
	}
	@Id
	@Column(name = "bundleCode",length = 30)
	public String getBundleCode() {
		return bundleCode;
	}
	public void setBundleCode(String bundleCode) {
		this.bundleCode = bundleCode;
	}
	
	@Column(name = "bundleDate")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBundleDate() {
		return bundleDate;
	}
	public void setBundleDate(Date bundleDate) {
		this.bundleDate = bundleDate;
	}
	
	@Column(name = "writeStatus")
	public String getWriteStatus() {
		return writeStatus;
	}
	public void setWriteStatus(String writeStatus) {
		this.writeStatus = writeStatus;
	}
	
	@Column(name = "putStatus")
	public String getPutStatus() {
		return putStatus;
	}
	public void setPutStatus(String putStatus) {
		this.putStatus = putStatus;
	}

}
