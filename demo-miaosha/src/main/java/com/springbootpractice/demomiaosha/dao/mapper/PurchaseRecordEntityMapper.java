package com.springbootpractice.demomiaosha.dao.mapper;

import com.springbootpractice.demomiaosha.dao.model.PurchaseRecordEntity;
import com.springbootpractice.demomiaosha.dao.model.PurchaseRecordEntityCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRecordEntityMapper {
    long countByExample(PurchaseRecordEntityCriteria example);

    int deleteByExample(PurchaseRecordEntityCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseRecordEntity record);

    int insertSelective(PurchaseRecordEntity record);

    List<PurchaseRecordEntity> selectByExample(PurchaseRecordEntityCriteria example);

    PurchaseRecordEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseRecordEntity record, @Param("example") PurchaseRecordEntityCriteria example);

    int updateByExample(@Param("record") PurchaseRecordEntity record, @Param("example") PurchaseRecordEntityCriteria example);

    int updateByPrimaryKeySelective(PurchaseRecordEntity record);

    int updateByPrimaryKey(PurchaseRecordEntity record);
}