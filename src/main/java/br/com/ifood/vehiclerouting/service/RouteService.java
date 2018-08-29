package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.ga.RouteChromosome;
import br.com.ifood.vehiclerouting.ga.VehicleRoutingGA;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VehicleRoutingGA ga;

    public RouteChromosome optmizePendingOrders () {

        /*final List<Order> ordersPending = orderRepository.findOrdersPending();

        return ga.optimize(ordersPending);*/

        return null;

    }
}
