package com.project.mums.payload;

public class SalesmanDto {
	private String salesmanno;
	private float commission;
    private String location;
	
    public SalesmanDto() {
		super();
	
	}

	public SalesmanDto(String salesmanno, float commission, String location) {
		super();
		this.salesmanno = salesmanno;
		this.commission = commission;
		this.location = location;
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
	
