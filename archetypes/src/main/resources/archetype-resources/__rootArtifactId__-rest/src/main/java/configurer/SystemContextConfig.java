package ${package}.configurer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 说明：初始化工具类InstanceFactory
 * @author ${projectAuthor}
 * 创建时间： 2020年02月13日 1:00 下午
 **/
@Component
@Slf4j
public class SystemContextConfig implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
       log.info("开始安装InstanceFactory");
//        SpringInstanceProvider provider = new SpringInstanceProvider(applicationContext);
//        InstanceFactory.setInstanceProvider(provider);
        log.info("开始安装InstanceFactory结束");
    }
}
