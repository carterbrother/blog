package com.authine.web.cola.domain.customer.extpt;

import com.alibaba.cola.extension.Extension;
import com.authine.web.cola.dto.domainmodel.Department;
import com.authine.web.cola.domain.customer.OrganizationExtPt;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author carter
 * create_date  2020/5/25 15:05
 * description     企业微信的扩展点实现
 */
@Extension(bizId = "organize",useCase = "getByCorpId",scenario = "wechat")
@Slf4j
public class WechatOrganizationExt  implements OrganizationExtPt {
    @Override
    public List<Department> getDepartmentsByCorpId(String corpId, Boolean includeDelete) {

        log.info("业务：组织机构，用例：通过企业编号获取部门 , 场景：企业微信");

        log.info("通过企业微信的API获取组织的部门信息，然后包装为需要的部门列表");

        Department department = new Department();

        department.setName("wechat");
        department.setCorpId(corpId);

        return Collections.singletonList(department);
    }
}
