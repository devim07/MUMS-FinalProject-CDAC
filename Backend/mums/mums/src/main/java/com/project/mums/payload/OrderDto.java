package com.project.mums.payload;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

public class OrderDto {
	@NotNull
	private int orderno;
	
	@NotNull
	private int custno;
	
	@NotNull
	@Min (value=100, message="Order of minimum 100 units should be place")
	private int orderUnit;
	
	@NotNull
	@PastOrPresent(message="Order date cant be a date in future")
    private Date orderDate;
	
	private String status;
    private String batchno;

    
    
    //Constructors, getters and setters
	public OrderDto() {
		super();
	}



	public OrderDto(int orderno, int custno, int orderUnit, Date orderDate, String status, String batchno) {
		super();
		this.orderno=orderno;
		this.custno = custno;
		this.orderUnit = orderUnit;
		this.orderDate = orderDate;
		this.status = status.toUpperCase();
		this.batchno = batchno;
	}

	public int getOrderno() {
		return orderno;
	}



	public void setOrderno(int orderno) {
		this.orderno = orderno;
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