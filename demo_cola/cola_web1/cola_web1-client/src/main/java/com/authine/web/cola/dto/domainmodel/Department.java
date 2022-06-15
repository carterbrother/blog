package com.authine.web.cola.dto.domainmodel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author carter
 * create_date  2020/5/25 14:29
 * description     部门领域
 */
@Data
public class Department {

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门主管")
    private String managerId;

    @ApiModelProperty("上级部门")
    private String parentId;

    @ApiModelProperty("工作日历")
    private String calendarId;

    @ApiModelProperty("排序号")
    private Long sortKey;

    @ApiModelProperty("是否叶子节点")
    private boolean leaf;

    @ApiModelProperty("来源id")
    private String sourceId;

    @ApiModelProperty(value = "数据查询编码",
            notes = "编码规则为： 上级queryCode+本级queryCOde，一级的queryCode范围为:0001-9999")
    private String queryCode;

    /**
     * 主管id集合（钉钉的部门主管id集合）
     */
    @ApiModelProperty(value = "主管id集合",
            notes = "钉钉的部门主管id集合")
    private String dingDeptManagerId;

    @ApiModelProperty("")
    private Department parent;

    @ApiModelProperty("")
    private List<Department> children;

    @ApiModelProperty("员工数量")
    private Integer employees;

//    @ApiModelProperty(value = "组织类型", example = UnitType.DESCRIPTION)
//    private UnitType unitType = UnitType.DEPARTMENT;

    @ApiModelProperty("是否可见：默认可见")
    private Boolean isShow = Boolean.TRUE;

//    @ApiModelProperty("类型 ：dept 部门 edu_dept 家校部门 ...用户组，圈子等需要自定义")
//    private DeptType deptType = DeptType.DEPT;

    @ApiModelProperty("组织唯一ID")
    private String corpId;

//    @ApiModelProperty("组织类型   0   MAIN 主组织 , 1   RELEVANCE  关联组织  ")
//    private OrgType relatedOrgType;
//
//
//    @ApiModelProperty("同步方式 0 PULL  拉取 ,1 PUSH 推送 ")
//    private OrgSyncType relatedSyncType;

    @ApiModelProperty("是否组织根节点 true：是，flase：否 ")
    private Boolean isCorpRootNode = Boolean.FALSE;

    @ApiModelProperty("是否展示维护功能 true：是，flase：否 ")
    private Boolean displayOption = Boolean.TRUE;


}
