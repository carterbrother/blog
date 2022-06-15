package com.authine.web.cola.dto;

import com.alibaba.cola.dto.Command;
import com.authine.web.cola.dto.domainmodel.Customer;
import lombok.Data;

@Data
public class CustomerAddCmd extends Command{

    private Customer customer;

}
