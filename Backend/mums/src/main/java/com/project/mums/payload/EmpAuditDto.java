package com.project.mums.payload;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpAuditDto {

	@NotNull
	private int serialno;
	
	@NotEmpty
	@Size(min=4, max=4, message="Employee ID should only contain 4 characters")
	private String empno;
	
	@NotEmpty
	private String user;
	
	@NotNull
	private LocalDateTime timestamp;
	
	@NotEmpty
	private String remark;
	
	

	public EmpAuditDto() {
		super();
	}

	public EmpAuditDto(int serialno, String empno, String user, LocalDateTime timestamp, String remark) {
		super();
		this.serialno = serialno;
		this.empno = empno.toUpperCase();
		this.user = user.toUpperCase();
		this.timestamp = timestamp;
		this.remark = remark.toUpperCase();
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
		this.empno = empno.toUpperCase();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user.toUpperCase();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark.toUpperCase();
	}
	
	
}