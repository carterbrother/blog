package com.authine.web.cola.domain.gateway;

import com.authine.web.cola.domain.customer.Customer;
import com.authine.web.cola.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
