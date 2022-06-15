package com.authine.web.cola.repository;

import com.authine.web.cola.domain.customer.Customer;
import com.authine.web.cola.domain.gateway.CustomerGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepository implements CustomerGateway {

    private  final CustomerMapper customerMapper;

    public CustomerRepository(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public Customer getByById(String customerId) {
        CustomerDO customerDO = customerMapper.getById(customerId);
        //Convert to Customer
        return Optional.ofNullable(customerDO)
                .map(item -> {
                    Customer customer = new Customer();
                    customer.setCompanyName(item.getCompanyName());
                    customer.setCustomerId(item.getCustomerId());
                    customer.setGlobalId(item.getGlobalId());
                    customer.setMemberId(item.getMemberId());
                    customer.setRegisteredCapital(item.getRegisteredCapital());
                    return customer;
                }).orElse(null);
    }
}
