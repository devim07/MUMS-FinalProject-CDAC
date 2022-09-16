package com.project.mums.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="ORDERS")
public class Order {

		@Id
		@Column(name="ONUM")
		private int orderno;
		
		@Column (name="CNUM")
		private int custno;
		
		@Column (name="SNUM")
		private String salesno;
		
		@Column (name="ORDER_UNIT")
		private int orderUnit;
		
		@Column (name="AMT")
		private float amount;
		
		@Column (name="ORDER_DATE")
	    private Date orderDate;
		
		@Column (name="STATUSS")
		private String status;
		
		@Column (name="BATCH_NO")
	    private String batchno;
	    
	    public Order() {
			super();
		}

		public Order(int orderno, int custno, String salesno, int orderUnit, float amount, Date orderDate,
				String status, String batchno) {
			super();
			this.orderno = orderno;
			this.custno = custno;
			this.salesno = salesno;
			this.orderUnit = orderUnit;
			this.amount = amount;
			this.orderDate = orderDate;
			this.status = status;
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

		public String getSalesno() {
			return salesno;
		}

		public void setSalesno(String salesno) {
			this.salesno = salesno;
		}

		public int getOrderUnit() {
			return orderUnit;
		}

		public void setOrderUnit(int orderUnit) {
			this.orderUnit = orderUnit;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
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
			this.status = status;
		}

		public String getBatchno() {
			return batchno;
		}

		public void setBatchno(String batchno) {
			this.batchno = batchno;
		}


		
	    

}
