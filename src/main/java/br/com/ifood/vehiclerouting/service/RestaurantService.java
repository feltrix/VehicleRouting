package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class RestaurantService {

    public Restaurant create(final Restaurant restaurant) {
        return restaurant;
    }

    public Restaurant update(Restaurant restaurant) {

        return restaurant;
    }

    public Restaurant find(@Valid long clientId) {
        return null;
    }
}
