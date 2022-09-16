package com.project.mums.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeptDto {
	
	@NotNull
	private char deptno;
	
	@NotEmpty
	@Size(min=4)
	private String deptname;
	
	@NotEmpty
	@Size(min=4, max=4)
	private String manager;

	public DeptDto() {
		super();
	}

	public DeptDto(char deptno, String deptname, String manager) {
		super();
		this.deptno = deptno;
		this.deptname = deptname.toUpperCase();
		this.manager = manager;
	}

	public char getDeptno() {
		return deptno;
	}

	public void setDeptno(char deptno) {
		this.deptno = deptno;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	
	
	
}
