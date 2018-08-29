package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,Long> {



}
