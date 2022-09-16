package com.project.mums.payload;

public class SalesmanDto {
	private String salesmanno;
	private float commition;
    private String location;
	
    public SalesmanDto() {
		super();
	
	}

	public SalesmanDto(String salesmanno, float commition, String location) {
		super();
		this.salesmanno = salesmanno;
		this.commition = commition;
		this.location = location;
	}

	public String getSalesmanno() {
		return salesmanno;
	}

	public void setSalesmanno(String salesmanno) {
		this.salesmanno = salesmanno;
	}

	public float getCommition() {
		return commition;
	}

	public void setCommition(float commition) {
		this.commition = commition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
    
    
}
	
