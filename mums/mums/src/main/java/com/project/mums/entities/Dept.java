package com.project.mums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEPT")
public class Dept {

	@Id
	@Column(name="DEPTNO")
	private char deptno;
	
	@Column(name="DNAME")
	private String deptname;
	
	private String manager;

	public Dept() {
		super();
		}

	public Dept(char deptno, String deptname, String manager) {
		super();
		this.deptno = deptno;
		this.deptname = deptname;
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
