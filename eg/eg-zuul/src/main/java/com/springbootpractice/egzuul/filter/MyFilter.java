package com.springbootpractice.egzuul.filter;

import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description zuul网关过滤器
 * @date 2019年06月20日 18:10
 * @Copyright (c) carterbrother
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public boolean shouldFilter() {
       return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest req = requestContext.getRequest();

        final String requestId = req.getParameter("requestId");

        if(Strings.isNullOrEmpty(requestId)){

            requestContext.setSendZuulResponse(false);

            requestContext.setResponseStatusCode(200);
            requestContext.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8.getType());
            requestContext.setResponseBody("{'success':false, 'message':'no requestId' }");

        }

        //放过
        return null;
    }


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
