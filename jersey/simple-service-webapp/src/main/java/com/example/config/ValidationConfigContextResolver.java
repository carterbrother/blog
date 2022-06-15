package com.example.config;

import org.glassfish.jersey.server.validation.ValidationConfig;
import org.glassfish.jersey.server.validation.internal.InjectingConstraintValidatorFactory;

import javax.validation.ParameterNameProvider;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2019年12月12日 10:04 上午
 **/
@Provider
public class ValidationConfigContextResolver implements ContextResolver<ValidationConfig> {

    @Context
    private ResourceContext resourceContext;

    @Override
    public ValidationConfig getContext(Class<?> type) {

        final InjectingConstraintValidatorFactory constraintValidatorFactory = resourceContext.getResource(InjectingConstraintValidatorFactory.class);
        ParameterNameProvider parameterNameProvider = new CustomerParameterNameProvider();
        return new ValidationConfig()
                .constraintValidatorFactory(constraintValidatorFactory)
                .parameterNameProvider(parameterNameProvider);
    }
}
