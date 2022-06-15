package com.authine.web.cola.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper{

  public CustomerDO getById(@Param("id") String customerId);
}
