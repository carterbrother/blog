package com.springx.bootdubbo.common.util;

import com.springx.bootdubbo.common.constants.RestConstant;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RequestUtil {

    /**
     *   是否multipart/form-data or application/octet-stream表单提交方式
     *
     * @param request
     * @return
     */
    public static final boolean isMultipartContent(HttpServletRequest request) {
        if (!RestConstant.POST_METHOD.equalsIgnoreCase(request.getMethod())) {
            return false;
        }
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        contentType = contentType.toLowerCase(Locale.ENGLISH);
        if (contentType.startsWith(RestConstant.MULTIPART)
                || RestConstant.APPLICATION_OCTET_STREAM.equals(contentType)) {
            return true;
        }
        return false;
    }

    protected static Map<String, Object> buildQueryParamsMap(HttpServletRequest request) {

        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> e = request.getParameterNames();

        StringBuilder tmpbuff = new StringBuilder();
        if (e.hasMoreElements()) {
            while (e.hasMoreElements()) {
                String name = e.nextElement();
                String[] values = request.getParameterValues(name);
                if (values.length == 1) {
                    if (StringUtils.isNotBlank(values[0]))
                        params.put(name, values[0]);
                } else {
                    tmpbuff.setLength(0);
                    for (int i = 0; i < values.length; i++) {
                        if (StringUtils.isNotBlank(values[i])) {
                            tmpbuff.append(values[i].trim()).append(",");
                        }
                    }
                    if (tmpbuff.length() > 0) {
                        tmpbuff.deleteCharAt(tmpbuff.length() - 1);
                        params.put(name, tmpbuff.toString());
                    }
                }
            }
        }
        return params;
    }

    /**
     * 解析请求来源--> protocal://host:port
     *
     * @return
     */
    public static String parseRequestOrigin(String... urls) {
        if (urls == null || urls.length == 0) {
            return null;
        }
        for (int i = 0; i < urls.length; i++) {
            String referer = urls[i];
            if (StringUtils.isEmpty(referer)) {
                continue;
            }
            URL url;
            try {
                url = new URL(referer);
            } catch (MalformedURLException e) {
                continue;
            }
            String host = url.getHost();
            String protocol = url.getProtocol();
            if (StringUtils.startsWith(protocol, "http")) {
                if (url.getPort() > 0 && url.getPort() != 80) {
                    return protocol + "://" + host + ":" + url.getPort();
                } else {
                    return protocol + "://" + host;
                }
            }
        }
        return null;
    }
}