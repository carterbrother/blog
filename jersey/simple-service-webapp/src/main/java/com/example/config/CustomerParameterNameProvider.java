package com.example.config;

import javax.validation.ParameterNameProvider;
import javax.validation.Validation;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 说明：修改参数的名字
 * @author carter
 * 创建时间： 2019年12月12日 10:43 上午
 **/
@Provider
public class CustomerParameterNameProvider implements ParameterNameProvider {

    private final ParameterNameProvider nameProvider;

    public CustomerParameterNameProvider() {
        nameProvider = Validation.byDefaultProvider().configure().getDefaultParameterNameProvider();
    }


    @Override
    public List<String> getParameterNames(Constructor<?> constructor) {
        return nameProvider.getParameterNames(constructor);
    }

    @Override
    public List<String> getParameterNames(Method method) {
        return nameProvider.getParameterNames(method).stream()
                .map("参数名是:"::concat)
                .collect(Collectors.toList());
    }
}
