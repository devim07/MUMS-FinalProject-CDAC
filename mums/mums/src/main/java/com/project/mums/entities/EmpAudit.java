package com.project.mums.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMP_AUDIT")
public class EmpAudit {

	@Id
	@Column(name="SR_NO")
	private int serialno;
	
	@Column(name="ENUM")
	private String empno;
	
	private String user;
	
	@Column(name="TIME_STAMP")
	private Date timestamp;
	
	private String remark;

	public EmpAudit() {
		super();
	}

	public EmpAudit(int serialno, String empno, String user, Date timestamp, String remark) {
		super();
		this.serialno = serialno;
		this.empno = empno;
		this.user = user;
		this.timestamp = timestamp;
		this.remark = remark;
	}

	public int getSerialno() {
		return serialno;
	}

	public void setSerialno(int serialno) {
		this.serialno = serialno;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
