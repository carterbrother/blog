package com.springbootpractice.demo.mybatis.dao.entity.handler;

import com.springbootpractice.demo.mybatis.dao.entity.enums.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月07日 5:11 下午
 **/

@MappedJdbcTypes(JdbcType.TINYINT)
@MappedTypes(SexEnum.class)
public class SexEnumTypeHandler extends BaseTypeHandler<SexEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getCode());
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return SexEnum.getByCode(resultSet.getInt(s));
    }

    @Override
    public SexEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return SexEnum.getByCode(resultSet.getInt(i));
    }

    @Override
    public SexEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return SexEnum.getByCode(callableStatement.getInt(i));

    }
}
