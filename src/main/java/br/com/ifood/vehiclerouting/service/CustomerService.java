package br.com.ifood.vehiclerouting.service;

import br.com.ifood.vehiclerouting.entity.Customer;
import br.com.ifood.vehiclerouting.exception.ResourceNotFoundRuntimeException;
import br.com.ifood.vehiclerouting.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired private MongoTemplate mongoTemplate;

    @Autowired private NextSequenceService nextSequenceService;

    public Customer create(final Customer customer) {
        long id = nextSequenceService.getNextSequence("customerSeq");
        customer.setId(id);
        return customerRepository.insert(customer);
    }

    public Customer find(@Valid long customerId) {

        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundRuntimeException("Customer", customerId));
    }

    public Customer update(final Customer customer) {

        if(customerRepository.existsById(customer.getId())) {

            return customerRepository.save(customer);

        }

        throw new ResourceNotFoundRuntimeException("Customer", customer.getId());

    }


}
