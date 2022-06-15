package com.authine.web.cola.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.authine.web.cola.dto.CustomerAddCmd;
import com.authine.web.cola.dto.CustomerListByNameQry;
import com.authine.web.cola.dto.domainmodel.Customer;

public interface CustomerServiceI {

    public Response addCustomer(CustomerAddCmd customerAddCmd);

    public MultiResponse<Customer> listByName(CustomerListByNameQry customerListByNameQry);
}
