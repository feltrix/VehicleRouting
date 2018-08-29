package br.com.ifood.vehiclerouting.repository;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {
}
