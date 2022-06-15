package com.authine.web.cola.api;

import com.alibaba.cola.dto.MultiResponse;
import com.authine.web.cola.dto.OrgnizationQry;
import com.authine.web.cola.dto.domainmodel.Department;

/**
 * @author carter
 * create_date  2020/5/25 15:22
 * description     二方库接口
 */

public interface OrganizationServiceI {

    /**
     * 通过企业id查询得到部门列表
     * @return 部门列表
     */
    MultiResponse<Department> getDepartmentsByCorpId(OrgnizationQry qry);

}
