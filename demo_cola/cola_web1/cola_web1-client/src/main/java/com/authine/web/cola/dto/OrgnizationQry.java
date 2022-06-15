package com.authine.web.cola.dto;

import com.alibaba.cola.dto.Query;
import com.alibaba.cola.extension.BizScenario;
import lombok.Data;

/**
 * @author carter
 * create_date  2020/5/25 15:11
 * description     组织结构查询参数
 */
@Data
public class OrgnizationQry extends Query {

    private String corpId;

    private boolean includeDelete;


    public static void main(String[] args) {

        OrgnizationQry orgnizationQry = new OrgnizationQry();
        orgnizationQry.setCorpId("0001");
        orgnizationQry.setIncludeDelete(true);
        orgnizationQry.setBizScenario(BizScenario.valueOf("organize","getByCorpId","dingTalk"));

        System.out.println("");
    }

}
