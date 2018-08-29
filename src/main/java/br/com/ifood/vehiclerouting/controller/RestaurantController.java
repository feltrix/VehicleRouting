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
    private RestaurantService restaurantService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new restaurant")
    public RestaurantVO create (@RequestBody @Valid final RestaurantVO restaurant) {

        return new RestaurantVO(restaurantService.create(new Restaurant(restaurant)));

    }

    @PutMapping(path = "/{restaurantId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Upadate a new restaurant by id")
    public RestaurantVO update (@PathVariable final Long restaurantId, @RequestBody @Valid final RestaurantVO restaurant) {

        final Restaurant restaurantForUpdate = new Restaurant(restaurant);
        restaurantForUpdate.setId(restaurantId);
        return new RestaurantVO(restaurantService.update(restaurantForUpdate));

    }

    @GetMapping(path = "/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a restaurant by id")
    public RestaurantVO find (@PathVariable final long restaurantId) {

        return new RestaurantVO(restaurantService.find(restaurantId));

    }

}
