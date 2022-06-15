package com.xxx.demo.api.dubbo.api;


/**
 * <groupId>com.lifesense.ihs</groupId>
 * <artifactId>guava-api</artifactId>
 * <version>1.0-SNAPSHOT</version>
 * <p>
 * <dubbo:reference id="sassUserService" interface="com.lifesense.ihs.saasuser.api.IsaasuserApi" retries="0"  check="false" timeout="${dubbo.consumer.timeout}" version="${lifesense.dubbo.version}"/>
 * @author 李福春
 * description 对系统之间的dubbo的API
 * created 2017年02月22日
 */
public interface ISaasUserApi {

    /**
     * 健康状态检查
     * @param name 检查的模块
     * @return 检查结果
     */
    String echo(String name);

}
