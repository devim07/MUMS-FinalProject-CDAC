package com.project.mums.payload;

import java.sql.Date;

public class EmpDto {
	private String empno;
	private float basicSal;    
	private char deptno;
	private char job;
    private String city;
    private String email;
    private Date hiredate;

    
    
    //Constructors, getters and setters
	public EmpDto() {
		super();
	}



	public EmpDto(String empno, float basicSal, char deptno, char job, String city, String email,
			Date hiredate) {
		super();
		this.empno = empno;
		this.basicSal = basicSal;
		this.deptno = deptno;
		this.job = job;
		this.city = city;
		this.email = email;
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


	
	public Date getHiredate() {
		return hiredate;
	}



	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	
}
