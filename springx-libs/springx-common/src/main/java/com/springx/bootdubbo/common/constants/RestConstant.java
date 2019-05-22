package com.springx.bootdubbo.common.constants;


/**
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description todo
 * @date 2019年05月22日 2:33 PM
 * @Copyright (c) carterbrother
 */
public class RestConstant {

    private RestConstant() {
    }

    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";

    public static final String PROP_REQUEST_BEGIN_TIME = "request_start_time";

    public static final String DEFAULT_AREA = "CN";

    public static final String DEFAULT_LANGUAGE = "zh";

    /**
     * Part of HTTP content type header.
     */
    public static final String MULTIPART = "multipart/";

    /**
     * HTTP content type header for multipart forms.
     */
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String SERVICENAME_PROP_NAME = "serviceName";


    public static final String ACCESS_CONTROL_ALLOW_METHODS_TITLE = "Access-Control-Allow-Methods";
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "POST, GET, OPTIONS";

    public static final String ALLOW_HEADERS = "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type";
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    public static final String ALLOW_ORIGIN = "*";
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = "Access-Control-Allow-Credentials";
    public static final String HTTP_HEADER_ORIGIN = "Origin";
    public static final String HTTP_HEADER_REFERER = "Referer";
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    /**
     * 慢应用跟踪阀值（毫秒）
     */
//    public static final long SLOW_THRESHOLD = Long.parseLong(ResourceUtils.get("slow_request_threshold", "10000"));


}
