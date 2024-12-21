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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DISH_ID")
	Long id;

	String name;
	String description;
	Double price;

	//fetch = FetchType.LAZY à éviter problem sérialisation 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("dishes")
	private User user;
	

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL) 
    @JsonIgnoreProperties("dish")
	private List<Reservation> reservations;

	

	public Dish(String name, String description, Double price, User user, List<Reservation> reservations) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
		this.reservations = reservations;
	}

	public Dish() {
	}

	public Dish(String name, String description, Double price, User user) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
