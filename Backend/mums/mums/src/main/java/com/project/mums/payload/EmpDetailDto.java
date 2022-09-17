package com.project.mums.payload;

import java.sql.Date;

public class EmpDetailDto {
	
	private String empno;
	private String ename;
   	private long mobileNumber;
    private char gender;
    private long aadhar;
    private Date dob;
    private String city;
    private int pinCode;   
    private String address;
    
    
    
	public EmpDetailDto(String empno, String ename, long mobileNumber, char gender, long aadhar, Date dob, String city,
			int pinCode, String address) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.aadhar = aadhar;
		this.dob = dob;
		this.city = city;
		this.pinCode = pinCode;
		this.address = address;
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
