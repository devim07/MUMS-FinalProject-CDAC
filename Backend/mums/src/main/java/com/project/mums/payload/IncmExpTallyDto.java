package com.project.mums.payload;

	import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

	public class IncmExpTallyDto {
		@NotEmpty
		private String head;
		
		@NotNull
		private float amount;
		
		@NotNull
	    private Date entrydate;
	    
	    @NotEmpty
		private String remark;

		
	    
	    public IncmExpTallyDto() {
			super();
		}

		public IncmExpTallyDto(String head, float amount, Date entrydate, String remark) {
			super();
			this.head = head;
			this.amount = amount;
			this.entrydate = entrydate;
			this.remark = remark;
		}

		public String getHead() {
			return head;
		}

		public void setHead(String head) {
			this.head = head;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public Date getEntrydate() {
			return entrydate;
		}

		public void setEntrydate(Date entrydate) {
			this.entrydate = entrydate;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		
	}
