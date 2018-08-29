package br.com.ifood.vehiclerouting.entity;

import br.com.ifood.vehiclerouting.vo.OrderVO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Document(collection = "orders")
public class Order {

	@Id
	private Long id;

	private final Restaurant restaurant;

	private final Customer customer;

	@Transient
	private LocalDateTime pickupDate;

	private Date pickup;

	@Transient
	private LocalDateTime deliveryDate;

	private Date delivery;

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

		this.pickup = orderVO.getPickup();

		this.delivery = orderVO.getDelivery();

		this.restaurant = restaurant;
		this.customer = customer;

		this.batchId = 0;
		this.status = "PENDING";
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getPickupDate() {
		if(pickupDate == null){
			this.pickupDate = pickup.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDateTime();
		}
		return pickupDate;
	}

	public LocalDateTime getDeliveryDate() {

		if(deliveryDate == null){
			this.deliveryDate = delivery.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDateTime();
		}
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

	public Date getPickup() {
		return pickup;
	}

	public void setPickup(Date pickup) {
		this.pickup = pickup;
	}

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
