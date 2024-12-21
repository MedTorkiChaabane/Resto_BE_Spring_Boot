package com.example.webTeam.validation.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private String pwd;
	private Long phone;
	private String address;
	private String speciality;
	private Long experience;
	private boolean isBlocked;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<Dish> dishes;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<Reservation> reservations;

	public User() {
	}

	public User(String firstName, String lastName, String email, String pwd, Long phone, String address,
			String speciality, Long experience, boolean isBlocked, List<Dish> dishes, List<Role> roles,
			List<Reservation> reservations) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.phone = phone;
		this.address = address;
		this.speciality = speciality;
		this.experience = experience;
		this.isBlocked = isBlocked;
		this.dishes = dishes;
		this.roles = roles;
		this.reservations = reservations;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	
	
	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}


	public Long getId() {
		return id;
	}

}
