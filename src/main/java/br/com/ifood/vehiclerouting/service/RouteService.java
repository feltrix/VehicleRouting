package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.ga.RouteChromosome;
import br.com.ifood.vehiclerouting.ga.VehicleRoutingGA;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class RouteService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private VehicleRoutingGA ga;

    public RouteChromosome optmizePendingOrders () {

        final long batchId = nextSequenceService.getNextSequence("batchSeq");

        uptadePedingOrdersToDoingStatus(batchId);

        List<Order> ordersPending = orderRepository.findByBatchId(batchId);

        if(ordersPending.size()>1) {

            final RouteChromosome optimized = ga.optimize(ordersPending);

            updateOrdersToDone(batchId);

            return optimized;

        } else {

            resetOrdersToPedingStatus(batchId);

        }

        return null;

    }

    private void uptadePedingOrdersToDoingStatus (long batchId) {

         mongoTemplate.updateMulti(
                query(where("status").is("PENDING")),
                new Update().set("status", "DOING").set("batchId", batchId),
                Order.class);

    }

    private void resetOrdersToPedingStatus (long batchId) {

        mongoTemplate.updateMulti(
                query(where("batchId").is(batchId)),
                new Update().set("status", "PENDING"),
                Order.class);

    }

    private void updateOrdersToDone (long batchId) {

        mongoTemplate.updateMulti(
                query(where("batchId").is(batchId)),
                new Update().set("status", "DONE"),
                Order.class);

    }
}
