package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,Long> {

    List<Order> findByStatus(String status);
    List<Order> findByBatchId(Long batchId);

}
