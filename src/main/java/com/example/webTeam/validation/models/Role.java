package com.example.webTeam.validation.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private List<User> users;
	
	

	public Role() {
		
	}
	

	public Role(String name, List<User> users) {
		this.name = name;
		this.users = users;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}
	
	

}
