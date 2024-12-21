package com.example.webTeam.validation.DTO;

public class LoginRequest {
	// Attributes
	private String email;
	private String pwd;

    // Gettres and setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}