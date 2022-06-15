package com.springbootpractice.demomiaosha.dao.mapper;

import com.springbootpractice.demomiaosha.dao.model.ProductEntity;
import com.springbootpractice.demomiaosha.dao.model.ProductEntityCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductEntityMapper {
    long countByExample(ProductEntityCriteria example);

    int deleteByExample(ProductEntityCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductEntity record);

    int insertSelective(ProductEntity record);

    List<ProductEntity> selectByExample(ProductEntityCriteria example);

    ProductEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductEntity record, @Param("example") ProductEntityCriteria example);

    int updateByExample(@Param("record") ProductEntity record, @Param("example") ProductEntityCriteria example);

    int updateByPrimaryKeySelective(ProductEntity record);

    int updateByPrimaryKey(ProductEntity record);

    /**
     * 悲观锁查询
     * @param productId 产品id
     * @return 得到的产品信息
     */
    ProductEntity selectByPessimisticLock(@Param("id") Long productId);

    /**
     * 乐观锁更新
     * @param updateParam 更新实体，有效内容 id,version,storeQty
     * @return 更新影响的行数
     */
    int updateByOptimisticLock(@Param("record") ProductEntity updateParam);
}