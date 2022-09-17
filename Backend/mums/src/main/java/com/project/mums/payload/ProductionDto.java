package com.project.mums.payload;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="PROD")
public class ProductionDto {

		@Id
		@Column(name="BATCH_NO")
		private int batchNo;
		
		@Column (name="PRO_DATE")
	    private Date productionDate;
		
		@Column(name="UNITS")
		private int units;

		

		
		public ProductionDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public ProductionDto(int batchNo, Date productionDate, int units) {
			super();
			this.batchNo = batchNo;
			this.productionDate = productionDate;
			this.units = units;
		}
		
		
		

		public int getBatchNo() {
			return batchNo;
		}

		public void setBatchNo(int batchNo) {
			this.batchNo = batchNo;
		}

		public Date getProductionDate() {
			return productionDate;
		}

		public void setProductionDate(Date productionDate) {
			this.productionDate = productionDate;
		}

		public int getUnits() {
			return units;
		}

		public void setUnits(int units) {
			this.units = units;
		}
		
		
	}
