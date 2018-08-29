package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.exception.ResourceNotFoundRuntimeException;
import br.com.ifood.vehiclerouting.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired private MongoTemplate mongoTemplate;

    @Autowired
    private NextSequenceService nextSequenceService;

    public Restaurant create(final Restaurant restaurant) {
        long id = nextSequenceService.getNextSequence("restaurantSeq");
        restaurant.setId(id);
        return restaurantRepository.insert(restaurant);
    }

    public Restaurant find(@Valid long restaurantId) {

        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundRuntimeException("Restaurant", restaurantId));
    }

    public Restaurant update(final Restaurant restaurant) {

        if(restaurantRepository.existsById(restaurant.getId())) {

            return restaurantRepository.save(restaurant);

        }

        throw new ResourceNotFoundRuntimeException("Restaurant", restaurant.getId());

    }
}
