package com.authine.web.cola.domain.customer.extpt;

import com.alibaba.cola.extension.Extension;
import com.authine.web.cola.dto.domainmodel.Department;
import com.authine.web.cola.domain.customer.OrganizationExtPt;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * @author carter
 * create_date  2020/5/25 14:32
 * description     企业部门在通过corpId获取部门列表的场景下，钉钉的扩展
 */
@Extension(bizId = "organize",useCase = "getByCorpId",scenario = "dingTalk")
@Slf4j
public class DingTalkOrganizationExt implements OrganizationExtPt {

    @Override
    public List<Department> getDepartmentsByCorpId(String corpId, Boolean includeDelete) {

        log.info("在组织结构业务，通过企业编号获取部门列表的用例，在钉钉的场景下业务的实现处理方式");

        log.info("通过钉钉的配置信息和API获取得到组织信息，并组装成云枢识别的部门信息");

        Department department = new Department();

        department.setName("dingTalk");
        department.setCorpId(corpId);

        return Collections.singletonList(department);
    }
}
