package br.com.ifood.vehiclerouting.ga;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Rider {
	
	private final List<Trip> trips;
	
	public Rider() {
		this.trips = new ArrayList<>();
	}
	
	public Trip addTrip(final Order order, final int tripId, final int riderId) {
		
		final Trip lastTrip;
		
		if(trips.isEmpty()) {
			lastTrip = null;
		}else {
			lastTrip = trips.get(trips.size()-1);
		}
		
		final Trip trip = new Trip(order,tripId,riderId,lastTrip);
		
		this.trips.add(trip);
		
		return trip;
	}
	
	public List<Trip> getTrips() {
		return trips;
	}
	
	/*public long countExtraChargeOverThreeInAllTrips() {
		
		long lastRestaurantId = -1L;
		long countOver = 0;
		long countCurrentSequence = 1;
		
		for(final Trip trip : trips) {
			
			if(trip.getOrder().getRestaurant().getId() == lastRestaurantId) {
				countCurrentSequence++;
			}else {
				countCurrentSequence = 1;
			}
			
			if(countCurrentSequence > 3) {
				countOver ++;
			}
			
		}
		
		return countOver;
		
	}*/

	public long countExtraRestaurants() {

		final long count = trips.stream()
				.map(Trip::getOrder)
				.map(Order::getRestaurant)
				.map(Restaurant::getId).distinct().count();

		return  Math.max(0,count-1);

	}

	public long countExtraChargeOver(final int chargeLimit) {

		final Map<Long, Long> collect = trips.stream()
				.map(Trip::getOrder)
				.map(Order::getRestaurant)
				.map(Restaurant::getId)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


		return collect.values().stream().filter(x-> x>chargeLimit).mapToLong(x-> x - chargeLimit).sum();

	}

	@Override
	public String toString() {
		return "Rider{" +
				"trips=" + trips +
				'}';
	}
}
