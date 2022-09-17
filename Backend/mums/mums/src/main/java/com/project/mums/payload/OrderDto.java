package com.project.mums.payload;

import java.sql.Date;

public class OrderDto {
	private int custno;
	private int orderUnit;
    private Date orderDate;
	private String status;
    private String batchno;

    
    
    //Constructors, getters and setters
	public OrderDto() {
		super();
	}



	public OrderDto(int custno, int orderUnit, Date orderDate, String status, String batchno) {
		super();
		this.custno = custno;
		this.orderUnit = orderUnit;
		this.orderDate = orderDate;
		this.status = status;
		this.batchno = batchno;
	}



	public int getCustno() {
		return custno;
	}



	public void setCustno(int custno) {
		this.custno = custno;
	}



	public int getOrderUnit() {
		return orderUnit;
	}



	public void setOrderUnit(int orderUnit) {
		this.orderUnit = orderUnit;
	}



	public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status.toUpperCase();
	}



	public String getBatchno() {
		return batchno;
	}



	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}



}