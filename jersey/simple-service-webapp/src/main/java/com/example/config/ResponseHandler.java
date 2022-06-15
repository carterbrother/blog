package com.example.config;

import com.example.param.RestResponse;
import org.glassfish.jersey.internal.guava.Maps;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 说明：统一的响应过滤器
 * @author carter
 * 创建时间： 2019年12月11日 2:19 下午
 **/
@Provider
public class ResponseHandler implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {


        responseContext.setStatus(200);


        final Object responseContextEntity = responseContext.getEntity();


        if (Objects.isNull(responseContextEntity)) {
            responseContext.setEntity(RestResponse.builder().code(200).message("success").build());
            return;
        }

        if (responseContextEntity instanceof RestResponse) {
            responseContext.setEntity(responseContextEntity);
            return;
        }

        Map<String, Object> dataMap = Maps.newHashMapWithExpectedSize(1);
        dataMap.put(responseContextEntity.getClass().getSimpleName(), responseContextEntity);
        responseContext.setEntity(RestResponse.builder()
                .code(200)
                .message("success")
                .data(dataMap)
                .build());


    }
}
