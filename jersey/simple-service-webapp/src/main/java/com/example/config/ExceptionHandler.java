package com.example.config;

import com.example.param.RestResponse;
import org.apache.logging.log4j.core.util.Throwables;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 说明：异常处理
 * @author carter
 * 创建时间： 2019年12月11日 1:58 下午
 **/
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    /**
     * Map an exception to a {@link Response}. Returning
     * {@code null} results in a {@link Response.Status#NO_CONTENT}
     * response. Throwing a runtime exception results in a
     * {@link Response.Status#INTERNAL_SERVER_ERROR} response.
     * @param exception the exception to map to a response.
     * @return a response mapped from the supplied exception.
     */
    @Override
    public Response toResponse(Exception exception) {
        return Response.ok()
                .entity(RestResponse.builder().code(500).message(Throwables.toStringList(exception).toString()).build())
                .build();
    }
}
