<?xml version="1.0" encoding="UTF-8"?>
<template>
    <comment ID="addJavaFileComment"></comment>
    <comment ID="addComment"></comment>
    <comment ID="addRootComment"></comment>
    <comment ID="addFieldComment"><![CDATA[
        <#if introspectedColumn??>
            /**
            <#if introspectedColumn.remarks?? && introspectedColumn.remarks != ''>
                <#list introspectedColumn.remarks?split("\n") as remark>
* ${remark}  对应数据库表字段: ${introspectedTable.fullyQualifiedTable}.${introspectedColumn.actualColumnName}
                </#list>
            </#if>
*/
        <#else>
        </#if>
        ]]></comment>
    <comment ID="addModelClassComment"><![CDATA[
/**
* @author: carter
* 对应数据库表： ${introspectedTable.fullyQualifiedTable}
*/
        ]]></comment>
    <comment ID="addClassComment"></comment>
    <comment ID="addEnumComment"></comment>
    <comment ID="addInterfaceComment"></comment>
    <comment ID="addGetterComment"></comment>
    <comment ID="addSetterComment"></comment>
    <comment ID="addGeneralMethodComment"></comment>
</template>
