package com.authine.web.cola.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.MultiResponse;
import com.authine.web.cola.api.OrganizationServiceI;
import com.authine.web.cola.dto.OrgnizationQry;
import com.authine.web.cola.dto.domainmodel.Department;
import org.springframework.stereotype.Service;

/**
 * @author carter
 * create_date  2020/5/25 15:29
 * description     组织机构实现业务类
 */
@Service
public class OrganizationServiceImpl implements OrganizationServiceI {

    private final CommandBusI commandBusI;


    public OrganizationServiceImpl(CommandBusI commandBusI) {
        this.commandBusI = commandBusI;
    }

    @Override
    public MultiResponse<Department> getDepartmentsByCorpId(OrgnizationQry qry) {
        return (MultiResponse<Department>) commandBusI.send(qry);
    }
}
