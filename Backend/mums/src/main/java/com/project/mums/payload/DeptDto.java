package com.project.mums.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeptDto {
	
	@NotNull
	@Size(min=1, max=1, message="Department ID should be a single letter")
	private String deptno;
	
	@NotEmpty
	@Size(min=4)
	private String deptname;
	
	
	@Size(min=4, max=4, message="Manager ID should contain only 4 characters")
	private String manager;

	public DeptDto() {
		super();
	}

	public DeptDto(String deptno, String deptname, String manager) {
		super();
		this.deptno = deptno.toUpperCase();
		this.deptname = deptname.toUpperCase();
		this.manager = manager.toUpperCase();
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno.toUpperCase();
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname.toUpperCase();
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager.toUpperCase();
	}

	
	
	
}