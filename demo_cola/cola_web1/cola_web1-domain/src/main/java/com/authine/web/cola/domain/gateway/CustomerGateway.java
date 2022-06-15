package com.authine.web.cola.domain.gateway;

import com.authine.web.cola.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
