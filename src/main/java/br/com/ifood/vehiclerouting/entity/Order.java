package br.com.ifood.vehiclerouting.entity;

import java.time.LocalDateTime;

public class Order {
	
	private final long id;
	private final Restaurant restaurant;
	private final Custumer custumer;
	private final LocalDateTime pickupDate;
	private final LocalDateTime deliveryDate;

	public Order() {
		id = 0;
		restaurant = null;
		custumer = null;
		pickupDate = null;
		deliveryDate = null;
	}
	
	public Order(int id, Restaurant restaurant, Custumer custumer,LocalDateTime pickupDate, LocalDateTime deliveryDate) {
		this.id = id;
		this.pickupDate = pickupDate;
		this.deliveryDate = deliveryDate;
		this.restaurant = restaurant;
		this.custumer = custumer;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getPickupDate() {
		return pickupDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public Custumer getCustumer() {
		return custumer;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", restaurant=" + restaurant +
				", custumer=" + custumer +
				", pickupDate=" + pickupDate +
				", deliveryDate=" + deliveryDate +
				'}';
	}
}
