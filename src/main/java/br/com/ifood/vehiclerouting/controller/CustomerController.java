package br.com.ifood.vehiclerouting.controller;

import br.com.ifood.vehiclerouting.entity.Customer;
import br.com.ifood.vehiclerouting.service.CustomerService;
import br.com.ifood.vehiclerouting.vo.CustomerVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/costumers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Create a new customer")
    public CustomerVO create (@RequestBody @Valid final CustomerVO customer) {

        return new CustomerVO(customerService.create(new Customer(customer)));

    }

    @PutMapping(path = "/{clientId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Upadate a new customer by id")
    public CustomerVO update (@PathVariable final Long clientId, @RequestBody @Valid final CustomerVO customer) {

        final Customer customerForUpdate = new Customer(customer);
        customerForUpdate.setId(clientId);
        return new CustomerVO(customerService.update(customerForUpdate));

    }

    @GetMapping(path = "/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a customer by id")
    public CustomerVO find (@PathVariable final long clientId) {

        return new CustomerVO(customerService.find(clientId));

    }

}
