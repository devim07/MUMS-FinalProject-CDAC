package com.project.mums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="OLD_SALARY")
public class OldSalary {
	@Id
	@Column(name="ENUM")
	private String empno;
	
	@Column(name="MONTH")
	private int month;
	
	@Column(name="YEAR")
	private int year;
	
	private char deptno;
	private char job;
	
	@Column(name="BASIC_SAL")
	private float basicsal;
	
	private int holidays;
	private String city;
	private float hra;
	private float totalsal;
	
	public OldSalary() {
		super();
	}
	
	public OldSalary(String empno, int month, int year, char deptno, char job, float basicsal, int holidays, String city,
			float hra, float totalsal) {
		super();
		this.empno = empno;
		this.month = month;
		this.year = year;
		this.deptno = deptno;
		this.job = job;
		this.basicsal = basicsal;
		this.holidays = holidays;
		this.city = city;
		this.hra = hra;
		this.totalsal = totalsal;
	}
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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
	public float getBasicsal() {
		return basicsal;
	}
	public void setBasicsal(float basicsal) {
		this.basicsal = basicsal;
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
	public float getHra() {
		return hra;
	}
	public void setHra(float hra) {
		this.hra = hra;
	}
	public float getTotalsal() {
		return totalsal;
	}
	public void setTotalsal(float totalsal) {
		this.totalsal = totalsal;
	}
	
	

}
