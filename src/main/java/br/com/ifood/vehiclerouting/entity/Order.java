package br.com.ifood.vehiclerouting.entity;

import br.com.ifood.vehiclerouting.vo.OrderVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Document(collection = "orders")
public class Order {

	@Id
	private Long id;

	private final Restaurant restaurant;

	private final Customer customer;

	private final LocalDateTime pickupDate;

	private final LocalDateTime deliveryDate;

	private long batchId;

	private String status;

	public Order() {
		id = 0L;
		restaurant = null;
		customer = null;
		pickupDate = null;
		deliveryDate = null;
	}
	
	public Order(long id, Restaurant restaurant, Customer customer,LocalDateTime pickupDate, LocalDateTime deliveryDate) {
		this.id = id;
		this.pickupDate = pickupDate;
		this.deliveryDate = deliveryDate;
		this.restaurant = restaurant;
		this.customer = customer;
	}

	public Order(OrderVO orderVO, Restaurant restaurant, Customer customer) {
		this.id = orderVO.getId();

		this.pickupDate = orderVO.getPickup().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		this.deliveryDate = orderVO.getDelivery().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();

		this.restaurant = restaurant;
		this.customer = customer;

		this.batchId = 0;
		this.status = "PENDING";
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
	
	public Customer getCustumer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", restaurant=" + restaurant +
				", custumer=" + customer +
				", pickupDate=" + pickupDate +
				", deliveryDate=" + deliveryDate +
				'}';
	}
}
