package com.authine.cloudpivot.app.flyway.test;

import com.alibaba.cola.event.EventBusI;
import com.authine.cloudpivot.app.flyway.dto.domainevent.InstallAppEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author carter
 * create_date  2020/6/30 10:33
 * description     测试事件总线
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = {
        "spring.cloud.nacos.discovery.namespace=lifc"})
public class EventTest {

    @Autowired
    private EventBusI eventBusI;

    @Test
    public void testPublisAppInstallEvent() {

        eventBusI.fireAll(new InstallAppEvent("test000009",5,1291));

    }
}
