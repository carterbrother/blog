package com.example.config;

import com.example.param.RestResponse;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 说明：异常处理
 * @author carter
 * 创建时间： 2019年12月11日 1:58 下午
 **/
@Provider
public class ValidExceptionHandler implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {

        final ConstraintViolationException violationException = (ConstraintViolationException) exception;
        final String errorMsg = violationException.getConstraintViolations().stream()
                .map(item -> "方法".concat(item.getPropertyPath().toString()).concat("值是 ")
                        .concat(Objects.toString(item.getInvalidValue()))
                        .concat("失败原因是:")
                        .concat(item.getMessage()))
                .collect(Collectors.joining(";"));

        return Response.ok()
                .type(MediaType.APPLICATION_JSON)
                .entity(RestResponse.builder()
                        .code(400)
                        .message(errorMsg).build())
                .build();
    }
}
