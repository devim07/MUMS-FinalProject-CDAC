package com.project.mums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="SALESMAN")
public class Salesman {

		@Id
		@Column(name="SNUM")
		private String salesmanno;
		
		@Column (name="COMM")
		private float commission;
		
		@Column (name="LOC")
		private String location;

		public Salesman(String salesmanno, float commission, String location) {
			super();
			this.salesmanno = salesmanno;
			this.commission = commission;
			this.location = location;
		}
          
		
		public Salesman() {
			super();
		}


		public String getSalesmanno() {
			return salesmanno;
		}

		public void setSalesmanno(String salesmanno) {
			this.salesmanno = salesmanno;
		}

		public float getCommission() {
			return commission;
		}

		public void setCommission(float commission) {
			this.commission = commission;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
		
		
}
