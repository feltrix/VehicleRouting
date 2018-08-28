package br.com.ifood.vehiclerouting.vo;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.ga.Rider;
import br.com.ifood.vehiclerouting.ga.Trip;

import java.util.List;
import java.util.stream.Collectors;

public class RouteVO {

    private final List<Long> orders;

    public RouteVO(Rider rider) {
        this.orders = rider.getTrips().stream()
                .map(Trip::getOrder)
                .map(Order::getId)
                .collect(Collectors.toList());
    }

    public List<Long> getOrders() {
        return orders;
    }
}
