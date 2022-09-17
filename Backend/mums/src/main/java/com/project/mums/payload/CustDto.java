package com.project.mums.payload;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustDto {
	@NotNull
	private int custno;
	
	@NotEmpty
	@Size(min=4, message="Name should atleast have 4 characters")
	private String custname;
	
	@NotEmpty
	@Size(min=4, max=4, message="City should only ontain 4 charactes")
	private String city;
	
	@NotNull
	@Min(value=0, message="Rating cannot be negative")
	@Max(value=10, message="Rating should be between 0 to 10")
	private  byte rating;
	
	
	
	
	public CustDto() {
		super();
	}

	public CustDto(int custno, String custname, String city, byte rating) {
		super();
		this.custno = custno;
		this.custname = custname.toUpperCase();
		this.city = city.toUpperCase();
		this.rating = rating;
	}

	public int getCustno() {
		return custno;
	}

	public void setCustno(int custno) {
		this.custno = custno;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname.toUpperCase();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city.toUpperCase();
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
	}
}