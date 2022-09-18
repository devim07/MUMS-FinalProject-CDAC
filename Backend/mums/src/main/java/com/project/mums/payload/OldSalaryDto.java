package com.project.mums.payload;

public class OldSalaryDto {
		private String empno;
		private char deptno;
		private int month;
		private int year;
		private float basicsal;
		private float holidays;
		private float hra;
		private Float comm;
		private float totalsal;
		
		
		
		
		
		public OldSalaryDto() {
			super();
		}
		
		public OldSalaryDto(String empno, char deptno, int month, int year, float basicsal, float holidays, float hra,
				Float comm, float totalsal) {
			super();
			this.empno = empno;
			this.deptno = deptno;
			this.month = month;
			this.year = year;
			this.basicsal = basicsal;
			this.holidays = holidays;
			this.hra = hra;
			this.comm = comm;
			this.totalsal = totalsal;
		}
		
		public String getEmpno() {
			return empno;
		}
		
		public void setEmpno(String empno) {
			this.empno = empno;
		}
		
		public char getDeptno() {
			return deptno;
		}
		
		public void setDeptno(char deptno) {
			this.deptno = deptno;
		}
		
		public int getMonth() {
			return month;
		}
		
		public void setMonth(int month) {
			this.month = month;
		}
		
		public int getYear() {
			return year;
		}
		public void setYear(int year) {
			this.year = year;
		}
		
		public float getBasicsal() {
			return basicsal;
		}
		
		public void setBasicsal(float basicsal) {
			this.basicsal = basicsal;
		}
		
		public float getHolidays() {
			return holidays;
		}
		
		public void setHolidays(float holidays) {
			this.holidays = holidays;
		}
		
		public float getHra() {
			return hra;
		}
		
		public void setHra(float hra) {
			this.hra = hra;
		}
		
		public Float getComm() {
			return comm;
		}
		
		public void setComm(Float comm) {
			this.comm = comm;
		}
		
		public float getTotalsal() {
			return totalsal;
		}
		
		public void setTotalsal(float totalsal) {
			this.totalsal = totalsal;
		}
		
		
		
		
		

}
