package com.project.mums.payload;

public class SalesmanOrderDto {
	private String snum;
	private int unit;
	
	
	public SalesmanOrderDto(String snum, int unit) {
		super();
		this.snum = snum;
		this.unit = unit;
	}


	public SalesmanOrderDto() {
		super();
	}


	public String getSnum() {
		return snum;
	}


	public void setSnum(String snum) {
		this.snum = snum;
	}


	public int getUnit() {
		return unit;
	}


	public void setUnit(int unit) {
		this.unit = unit;
	}
	
	
	

}
