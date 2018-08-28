package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Position;

import java.time.Duration;
import java.time.LocalDateTime;

public class Trip {
	
	private final Order order;
	private final int riderTripId;
	private final int riderId;
	private final LocalDateTime startTime;
	private final LocalDateTime pickupTime;
	private final LocalDateTime deliveryTime;
	private final Trip lastTrip;

	public Trip() {
		this.order = null;
		this.riderTripId = 0;
		this.riderId = 0;
		this.startTime = null;
		this.pickupTime = null;
		this.deliveryTime = null;
		this.lastTrip = null;
	}

	public Trip(final Order order, final int riderTripId, final int riderId, final Trip lastTrip) {
		
		this.order = order;
		this.riderTripId = riderTripId;
		this.riderId = riderId;
		this.lastTrip = lastTrip;
		
		long totalTimeInMinutes = 0; 
		
		if(lastTrip == null) {
			startTime = order.getPickupDate();
			pickupTime = order.getPickupDate();
			Position p1 = order.getRestaurant().getPosition();
			Position p2 = order.getCustumer().getPosition();
			totalTimeInMinutes = caculateTime(p1, p2);
		}else if(lastTrip.order.getRestaurant().equals(order.getRestaurant())) {
			startTime = lastTrip.getDeliveryTime();
			pickupTime = lastTrip.getPickupTime();
			Position p1 = lastTrip.getOrder().getCustumer().getPosition();
			Position p2 = order.getCustumer().getPosition();
			totalTimeInMinutes = caculateTime(p1,p2);
		} else {
			
			startTime = lastTrip.getDeliveryTime();
			
			Position p1 = lastTrip.getOrder().getCustumer().getPosition();
			Position p2 = order.getRestaurant().getPosition();
			Position p3 = order.getCustumer().getPosition();
			
			long t1 = caculateTime(p1, p2);
			long t2 = caculateTime(p2, p3);
			
			pickupTime = startTime.plusMinutes(t1);
			
			totalTimeInMinutes =  t1 + t2;
			
		}
		
		deliveryTime = startTime.plusMinutes(totalTimeInMinutes);
		
	}
	
	private long caculateTime(Position p1, Position p2) {
		Double time = (p1.calculateDistance(p2)/0.1) * 5;
		return time.longValue();
	}
	
	private boolean isPickedUpOnTime() {
		return this.pickupTime.compareTo(order.getPickupDate()) >= 0;
	}
	
	private boolean isDeliveredOnTime() {
		return this.deliveryTime.compareTo(order.getDeliveryDate()) <=0;
	}

	public long howManyMinutesDeliveryIsLate () {
		if(isDeliveredOnTime()) {
			return 0;
		}
		return Duration.between(order.getDeliveryDate(), this.deliveryTime).getSeconds()/60;
	}

	public long howManyMinutesPickupIsAhead () {
		if(isPickedUpOnTime()) {
			return 0;
		}
		return Duration.between(this.pickupTime, order.getPickupDate()).getSeconds()/60;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public LocalDateTime getPickupTime() {
		return pickupTime;
	}

	@Override
	public String toString() {
		return "Trip{" +
				"order=" + order +
				", riderTripId=" + riderTripId +
				", riderId=" + riderId +
				", startTime=" + startTime +
				", pickupTime=" + pickupTime +
				", deliveryTime=" + deliveryTime +
				", lastTrip=" + lastTrip +
				'}';
	}
}
