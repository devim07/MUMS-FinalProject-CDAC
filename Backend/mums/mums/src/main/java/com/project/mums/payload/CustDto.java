package com.project.mums.payload;

public class CustDto {
		private int custno;
		private String custname;
		private String city;
		private  byte rating;
		
		public CustDto() {
			super();
			}

		public CustDto(int custno, String custname, String city, byte rating) {
			super();
			this.custno = custno;
			this.custname = custname;
			this.city = city;
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
			this.custname = custname;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public byte getRating() {
			return rating;
		}

		public void setRating(byte rating) {
			this.rating = rating;
		}
		
		
		

		
	

}
