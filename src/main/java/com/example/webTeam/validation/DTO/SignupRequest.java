package com.example.webTeam.validation.DTO;

import java.util.List;

public class SignupRequest {
	
	//attributes
	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private Long phone;
	private String address;
	private String speciality;
	private Long experience;
	private boolean isBlocked;
	

	private List<String> roles;
	
	// Gettres and setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Long getExperience() {
		return experience;
	}
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}


	

}
