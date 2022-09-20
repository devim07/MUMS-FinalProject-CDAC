package com.project.mums.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="EMP_DETAILS")
public class EmpDetail {
	
	
	@Id
	@Column(name="ENUM",length=4)
	private String empno;
	
	@Column(name="ENAME",length=4)
	private String ename;
    //ENAME VARCHAR(25) NOT NULL,
	
	@Column(name="MOB",length=10,nullable=false)
	private long mobileNumber;
    //MOB BIGINT NOT NULL
	
	@Column (name="GEN", nullable=false)
    private char gender;
    //GEN CHAR(1) NOT NULL
   // CONSTRAINT CH_EMP_GEN CHECK (GEN IN('M', 'F', 'T')),
    
    @Column (name="AADHAR", nullable=false)
    private long aadhar;
   // AADHAR BIGINT NOT NULL
   // CONSTRAINT CH_EMP_AADHAR CHECK (LENGTH(AADHAR)=12),
    
    @Column (name="DOB", nullable=false)
    private Date dob;
    //DOB DATE NOT NULL,
    
    @Column (name="CITY", nullable=false)
    private String city;
   // CITY VARCHAR(4) NOT NULL,
    
    @Column (name="PINCODE", nullable=false)
    private int pinCode;
    //PINCODE INT NOT NULL
     // CONSTRAINT CH_EMP_PINCODE CHECK (LENGTH(PINCODE)=6),
    
    private String address;
   //    ADDRESS TEXT,
    
   
	public String getEmpno() {
		return empno;
	}
	
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename;
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
		this.city = city;
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
		this.address = address;
	}
	
	
	public EmpDetail(String empno, String ename, long mobileNumber, char gender, long aadhar, Date dob, String city,
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
	
	
	public EmpDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
}