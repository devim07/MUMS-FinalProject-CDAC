package com.project.mums.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class LoginDto {
	
	@NotEmpty
	@Size(min=4, max=25, message="UserName  should  contain min  4 characters")
	private String userName;
	
	
	@Valid
	@NonNull
	@NotBlank(message = "New password is mandatory")
    private String password;
	
	@NotEmpty
    private String role;
	
	@NotBlank(message = "New ID is mandatory")
	private String ID;

	
	
	
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDto(
			@NotEmpty @Size(min = 4, max = 25, message = "UserName  should  contain min  4 characters") String userName,
			@Valid @NotBlank(message = "New password is mandatory") String password, @NotEmpty String role,
			@NotBlank(message = "New ID is mandatory") String ID) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.ID = ID;
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

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	
    
    
    
}
