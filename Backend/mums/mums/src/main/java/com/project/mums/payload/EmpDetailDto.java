package com.project.mums.payload;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class EmpDetailDto {
	
	@NotEmpty
	@Size(min=4, max=4, message="Employee ID should only contain 4 characters")
	private String empno;
	
	@NotEmpty
	@Size(min=4, max=25, message="Employee name should have 4-25 characters")
	private String ename;
	
	@NotNull
   	private long mobileNumber;
	
	@NotNull
    private char gender;
	
	@NotNull
    private long aadhar;
	
	@NotNull
	@Past(message="Date of birth cant be a date in future")
    private Date dob;
	
	@NotEmpty
	@Size(min=4, max=4, message="City should only ontain 4 charactes")
    private String city;
	
	@NotNull
	@Min(value=100000, message="Pincode should only contain 6 digits")
	@Max(value=999999, message="Pincode should only contain 6 digits")
    private int pinCode;  
	
	@NotEmpty
    private String address;
	
    
    
    
    public EmpDetailDto(String empno, String ename, long mobileNumber, char gender, long aadhar, Date dob, String city,
			int pinCode, String address) {
		super();
		this.empno = empno.toUpperCase();
		this.ename = ename.toUpperCase();
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.aadhar = aadhar;
		this.dob = dob;
		this.city = city.toUpperCase();
		this.pinCode = pinCode;
		this.address = address.toUpperCase();
	}
	
	public EmpDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getEmpno() {
		return empno;
	}
	
	public void setEmpno(String empno) {
		this.empno = empno.toUpperCase();
	}
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename.toUpperCase();
	}
	
	public long getMobileNumber() {
	
		return mobileNumber;
	}
	
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	public char getGender() {
		return gender;
	}
	
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public long getAadhar() {
		return aadhar;
	}
	
	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	public String getCity() {
		return city;
	}
	
	
	public void setCity(String city) {
		this.city = city.toUpperCase();
	}
	
	
	public int getPinCode() {
		return pinCode;
	}
	
	
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	
	public void setAddress(String address) {
		this.address = address.toUpperCase();
	}
   
    
}
