package com.authine.web.cola.executor.query;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.authine.web.cola.dto.domainmodel.Department;
import com.authine.web.cola.domain.customer.OrganizationExtPt;
import com.authine.web.cola.dto.OrgnizationQry;

import java.util.List;


/**
 * @author carter
 * create_date  2020/5/25 15:09
 * description     查询组织机构的指令执行
 */
@Command
public class OrganizationQueryExe implements CommandExecutorI<MultiResponse, OrgnizationQry> {


    private final ExtensionExecutor extensionExecutor;

    public OrganizationQueryExe(ExtensionExecutor extensionExecutor) {
        this.extensionExecutor = extensionExecutor;
    }


    @Override
    public MultiResponse execute(OrgnizationQry cmd) {

        String corpId = cmd.getCorpId();

        boolean includeDelete = cmd.isIncludeDelete();

        List<Department> departmentList = extensionExecutor.execute(OrganizationExtPt.class, cmd.getBizScenario(),
                ex -> ex.getDepartmentsByCorpId(corpId, includeDelete));


        return MultiResponse.ofWithoutTotal(departmentList);
    }
}
