package com.project.mums.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="EMP")
public class Emp {
	@Id
	@Column(name="ENUM",length=4)
	private String empno;
	
	@Column (name="BASIC_SAL", nullable=false)
	private float basicSal;
    
	private char deptno;
	private char job;
    private int holidays;
    private String city;
    private String email;
    private float hra;
    private float totsal;
    private Date hiredate;

    
    
    
//constructors, getters and setters    
	public Emp() {
		super();
	}


	public Emp(String empno, float basicSal, char deptno, char job, String city, String email, float hra,
			Date hiredate) {
		super();
		this.empno = empno;
		this.basicSal = basicSal;
		this.deptno = deptno;
		this.job = job;
		this.city = city;
		this.email = email;
		this.hra = hra;
		this.hiredate = hiredate;
	}


	public String getEmpno() {
		return empno;
	}


	public void setEmpno(String empno) {
		this.empno = empno;
	}


	public float getBasicSal() {
		return basicSal;
	}


	public void setBasicSal(float basicSal) {
		this.basicSal = basicSal;
	}


	public char getDeptno() {
		return deptno;
	}


	public void setDeptno(char deptno) {
		this.deptno = deptno;
	}


	public char getJob() {
		return job;
	}


	public void setJob(char job) {
		this.job = job;
	}


	public int getHolidays() {
		return holidays;
	}


	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public float getHra() {
		return hra;
	}


	public void setHra(float hra) {
		this.hra = hra;
	}


	public float getTotsal() {
		return totsal;
	}


	public void setTotsal(float totsal) {
		this.totsal = totsal;
	}


	public Date getHiredate() {
		return hiredate;
	}


	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

}