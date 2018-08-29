package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.exception.ResourceNotFoundRuntimeException;
import br.com.ifood.vehiclerouting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired private MongoTemplate mongoTemplate;

    @Autowired
    private NextSequenceService nextSequenceService;

    public Order create(final Order order) {
        long id = nextSequenceService.getNextSequence("orderSeq");
        order.setId(id);
        return orderRepository.insert(order);
    }

    public Order find(long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundRuntimeException("Order", orderId));
    }

    public List<Order> findAllPending() {

        return orderRepository.findByStatus("PENDING");
    }

    public Order update(final Order order) {

        if(orderRepository.existsById(order.getId())) {

            return orderRepository.save(order);

        }

        throw new ResourceNotFoundRuntimeException("Order", order.getId());

    }
    
}
