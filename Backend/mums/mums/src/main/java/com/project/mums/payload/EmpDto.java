package com.project.mums.payload;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

public class EmpDto {
	
	@NotEmpty
	@Size(min=4, max=4, message="Employee ID should only contain 4 characters")
	private String empno;
	
	@NotNull
	@Min (value=15000, message="Basic salary should be greater than Rs. 15000")
	@Max (value=50000, message="Basic salary should be less than Rs. 50000")
	private float basicSal;
	
	@NotNull
	private char deptno;
	
	@NotNull
	private char job;
	
	@NotEmpty
	@Size(min=4, max=4, message="City should only ontain 4 charactes")
    private String city;
	
	@NotEmpty
	@Email(message="Enter proper e-mail address")
    private String email;
	
	@NotNull
	@PastOrPresent(message="Hiredate cant be a date in future")
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
