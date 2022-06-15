package com.authine.web.cola.domain.customer;

import com.alibaba.cola.extension.ExtensionPointI;
import com.authine.web.cola.dto.domainmodel.Department;

import java.util.List;

/**
 * @author carter
 * create_date  2020/5/25 14:25
 * description     定义扩展点接口，对组织机构的某些方法。
 */

public interface OrganizationExtPt extends ExtensionPointI {

    /**
     * 根据corpId查询企业下所有部门
     *
     * @param corpId        企业编号
     * @param includeDelete 是否包含删除的部门
     * @return 部门
     */
    List<Department> getDepartmentsByCorpId(String corpId, Boolean includeDelete);


}
