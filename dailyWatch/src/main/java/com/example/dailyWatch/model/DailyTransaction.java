package com.example.dailyWatch.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyTransaction {
	
	
	private String companyName;
	private int dailyClose;
	private int dailyLow;
	private int dailyHigh;
	private String companyType;
	private Date dateOfTransaction;
	@Id
	@GeneratedValue
	private int serialid;
	
	public DailyTransaction(){
		   super();
		}
	
	public DailyTransaction(String companyName, int dailyClose, int dailyLow, int dailyHigh, String companyType, Date dateOfTransaction) 
	{ 
		this.companyName = companyName; 
		this.dailyClose = dailyClose; 
		this.dailyLow = dailyLow; 
		this.dailyHigh = dailyHigh; 
		this.companyType = companyType; 
		this.dateOfTransaction = dateOfTransaction;
		
	
	
	}

	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public int getDailyClose() {
		return dailyClose;
	}
	
	public void setDailyClose(int dailyClose) {
		this.dailyClose = dailyClose;
	}
	
	public int getDailyLow() {
		return dailyLow;
	}
	
	public void setDailyLow(int dailyLow) {
		this.dailyLow = dailyLow;
	}
	
	public int getDailyHigh() {
		return dailyHigh;
	}
	
	public void setDailyHigh(int dailyHigh) {
		this.dailyHigh = dailyHigh;
	}
	
	public String getCompanyType() {
		return companyType;
	}
	
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	

	public int getSerialid() {
		return serialid;
	}

	public void setSerialid(int serialid) {
		this.serialid = serialid;
	}

	@Override
	public String toString() {
		return "DailyTransaction [companyName=" + companyName + ", dailyClose=" + dailyClose + ", dailyLow=" + dailyLow
				+ ", dailyHigh=" + dailyHigh + ", companyType=" + companyType + ", dateOfTransaction="
				+ dateOfTransaction + "]";
	}
	
	
	
}
