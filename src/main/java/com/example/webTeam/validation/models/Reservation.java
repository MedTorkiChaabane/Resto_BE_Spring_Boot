package com.example.webTeam.validation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RESERVATION_ID")
	private Long id;
	
	private Long quantity;
	



	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "dish_id") 
	@JsonIgnoreProperties("reservations")
	private Dish dish;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user_id") 
	@JsonIgnoreProperties("reservations")
	private User user;

	
	public Reservation() {
	}


	public Reservation(Long quantity, Dish dish, User user) {
		this.quantity = quantity;
		this.dish = dish;
		this.user = user;
	}


	public Dish getDish() {
		return dish;
	}


	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}
	
	

}
