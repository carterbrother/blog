package com.authine.web.cola.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.extension.BizScenario;
import com.authine.web.cola.api.OrganizationServiceI;
import com.authine.web.cola.dto.OrgnizationQry;
import com.authine.web.cola.dto.domainmodel.Department;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationController {

    private final OrganizationServiceI organizationServiceI;

    public OrganizationController(OrganizationServiceI organizationServiceI) {
        this.organizationServiceI = organizationServiceI;
    }

    @GetMapping(value = "/organization/getDepartmentsByCorpId/{corpId}/{scenario}")
    public MultiResponse<Department> listCustomerByName(@PathVariable("corpId") String corpId,@PathVariable("scenario") String scenario){

        OrgnizationQry qry = new OrgnizationQry();
        qry.setCorpId(corpId);
        qry.setIncludeDelete(true);
        qry.setBizScenario(BizScenario.valueOf("organize","getByCorpId",scenario));

        return organizationServiceI.getDepartmentsByCorpId(qry);
    }


}
