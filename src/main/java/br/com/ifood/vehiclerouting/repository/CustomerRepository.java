package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
}
