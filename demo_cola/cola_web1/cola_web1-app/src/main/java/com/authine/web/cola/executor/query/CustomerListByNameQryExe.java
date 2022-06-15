package com.authine.web.cola.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.QueryExecutorI;
import com.alibaba.cola.dto.MultiResponse;
import com.authine.web.cola.domain.gateway.CustomerGateway;
import com.authine.web.cola.dto.CustomerListByNameQry;
import com.authine.web.cola.dto.domainmodel.Customer;
import com.authine.web.cola.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Command
public class CustomerListByNameQryExe implements QueryExecutorI<MultiResponse<Customer>, CustomerListByNameQry> {

    private final CustomerGateway customerGateway;

    public CustomerListByNameQryExe(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public MultiResponse<Customer> execute(CustomerListByNameQry cmd) {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = customerGateway.getByById(cmd.getName());
        customerList.add(customer);
        return MultiResponse.ofWithoutTotal(customerList);
    }
}
