package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Customer;
import br.com.ifood.vehiclerouting.entity.Order;
import br.com.ifood.vehiclerouting.entity.Restaurant;
import br.com.ifood.vehiclerouting.service.CustomerService;
import br.com.ifood.vehiclerouting.service.OrderService;
import br.com.ifood.vehiclerouting.service.RestaurantService;
import br.com.ifood.vehiclerouting.vo.OrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new order")
    public OrderVO create (@RequestBody @Valid final OrderVO order) {


        Restaurant restaurant = restaurantService.find(order.getRestaurantId());
        Customer customer = customerService.find(order.getClientId());

        return new OrderVO(orderService.create(new Order(order,restaurant, customer)));

    }

    @PutMapping(path = "/{orderId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Upadate a new order by id")
    public OrderVO update (@PathVariable final Long orderId, @RequestBody @Valid final OrderVO order) {

        Restaurant restaurant = restaurantService.find(order.getRestaurantId());
        Customer customer = customerService.find(order.getClientId());

        final Order orderForUpdate = new Order(order,restaurant,customer);
        orderForUpdate.setId(orderId);
        return new OrderVO(orderService.update(orderForUpdate));

    }

    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a order by id")
    public OrderVO find (@PathVariable final long orderId) {

        return new OrderVO(orderService.find(orderId));

    }

    @GetMapping(path = "/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find all pending")
    public List<Order> findFull () {

        return orderService.findAllPending();

    }


}
