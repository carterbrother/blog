package com.example.demo_unittest;

import com.example.demo_unittest.config.MyConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = MyConfig.class,initializers = ConfigDataApplicationContextInitializer.class)
class ControllerTests {

    private final ConfigurableApplicationContext applicationContext;

    ControllerTests(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Test
    void contextLoads() {

        TestPropertyValues.of("a=1","b=c").applyTo(applicationContext);

    }

}
