package com.project.mums.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INCM_EXP_TALLY")
public class IncmExpTally {
	
	private String head;
	
	@Column(name="AMT")
	private Float amount;
	
	@Column(name="ENTRY_DATE")
	private Date entrydate;
	
	@Id
	private String remark;

	public IncmExpTally() {
		super();
	}

	public IncmExpTally(String head, Float amount, Date entrydate, String remark) {
		super();
		this.head = head;
		this.amount = amount;
		this.entrydate = entrydate;
		this.remark = remark;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
