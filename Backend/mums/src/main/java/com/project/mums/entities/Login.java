package com.project.mums.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="LOGIN")
public class Login {

	@Id
	@Column(name="USERNAME")
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String role;
	
	@Column(nullable=false)
	private String ID;

	
	
	
	

	public Login(String userName, String password, String role, String ID) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.ID = ID;
	}

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getid() {
		return ID;
	}

	public void setid(String ID) {
		this.ID = ID;
	}

	
	
	
}
