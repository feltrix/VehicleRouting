package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.service.RestaurantService;
import br.com.ifood.vehiclerouting.vo.RestaurantVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService RestaurantService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new Restaurant")
    public Restaurant create(@RequestBody @Valid final RestaurantVO restaurantVO) {

        return RestaurantService.create(new Restaurant(restaurantVO));

    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Upadate a new Restaurant by id")
    public Restaurant update(@RequestBody @Valid final RestaurantVO restaurantVO) {

        return RestaurantService.update(new Restaurant(restaurantVO));

    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a Restaurant by id")
    public Restaurant find(@Valid final long clientId) {

        return RestaurantService.find(clientId);

    }

}