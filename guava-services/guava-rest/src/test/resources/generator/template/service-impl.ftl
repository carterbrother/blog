package ${basePackage}.biz.service.impl;

import ${basePackage}.dal.mapper.${modelNameUpperCamel}Mapper;
import ${basePackage}.dal.model.${modelNameUpperCamel};
import ${basePackage}.biz.service.${modelNameUpperCamel}Service;
import ${basePackage}.biz.configurer.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
