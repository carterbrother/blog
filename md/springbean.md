# 30 springBean的生命周期？

![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1585990260913-2048e915-3855-4891-a4da-df136613520f.png#align=left&display=inline&height=300&name=image.png&originHeight=300&originWidth=533&size=86199&status=done&style=none&width=533)

spring是Java软件开发的事实标准。


我是李福春，我在准备面试，今天的问题是：springBean的生命周期是怎样的？


答：spring最基础的能力是IOC(依赖注入),AOP（面向切面编程），ioc改善了模块之间的耦合问题，依赖注入的方式：set方法，构造方法，成员变量+ `@Autowire`  ；Bean的管理是IOC的主要功能。
bean的生命周期完全由spring容器管理，从属性设置到各种依赖关系的注入，简化了开发人员对bean的生命周期认知；Spring的容器中Bean生命周期如下：


**对象创建**


1，从xml配置的Bean,@Bean注解，或者Java代码`BeanDefinitionBuilder`中读取Bean的定义,实例化Bean对象；


2，设置Bean的属性；


3，注入Aware的依赖（BeanNameAware,BeanFactoryAware,ApplicationContextAware）;


4, 执行通用的方法前置处理，方法： `BeanPostProcessor.postProcessorBeforeInitialization()` 


5, 执行 `InitalizingBean.afterPropertiesSet()` 方法


6，执行Bean自定义的初始化方法init,或者 `@PostConstruct` 标注的方法；


7，执行方法`BeanPostProcessor.postProcessorAfterInitialization()`


8, 创建对象完毕；


**对象销毁**


9， 执行 `DisposableBean.destory()` 方法；
**
10，执行自定义的destory方法或者 `@PreDestory` 标注的方法；


11，销毁对象完毕




下面扩展一下spring的bean关联面试问题。


# Bean的作用域



| 作用域 | 说明 |
| --- | --- |
| singleton | 单例，适合无状态的bean |
| prototype | 每次注入都是一个新的对象，适合有状态的bean，需要注意创建对象的开销 |
| request | web容器中的对象，每次请求都创建一个对象 |
| session | web容器中的对象，一次会话创建一个对象 |
| globalSession | web容器全局的对象 |



# 面向切面编程


aop：为了java的应用更好的模块化，解决程序中的事务，安全，日志等问题；
使用java的动态代理（接口代理）或者CGlib（扩展代理类）实现。


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1585993039738-b8044be3-35f4-495a-a7bd-d04bf82d96b6.png#align=left&display=inline&height=290&name=image.png&originHeight=580&originWidth=1028&size=68410&status=done&style=none&width=514)


主要元素如下图：
Aspect: 定义切面 ， Advice定义增强，增强的类型见上图；
pointcut: 切点，即切入哪些类的哪些方法；


![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1585993054031-773d7a93-cd4d-4cc1-888e-ae7155b81a56.png#align=left&display=inline&height=402&name=image.png&originHeight=804&originWidth=1022&size=89949&status=done&style=none&width=511)


spring应用中使用AOP的实例代码：


```java
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method

/**
 * 服务层AOP
 */
@Aspect
@Service
@Order(0)
@Slf4j
public class ServiceInterceptor {

    @Pointcut("execution(public * com.xxx.ihs.yyy.admin.manager..*.*(..))")
    public void pointcut() {
    }

    private String serviceName() {
        return "xxxrest";
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
       
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        final String methodName = method.getDeclaringClass().getCanonicalName().concat(".").concat(method.getName());
        Object[] args = pjp.getArgs();
        Object returnObj = null;
        try {
            returnObj = pjp.proceed();
        } catch (Exception ex) {
            String message = String.format("[%s]服务发生系统错误:%s", this.serviceName(), ex.getMessage());
            log.error("{}, method:{},args:{}", message, methodName, JsonUtils.toJson(args),  ex);
            throw ex;
        } 
        return returnObj;
    }
}
```


# 小结


本篇回答了spring的ioc容器的Bean生命周期的过程。
然后简单的说明了一下Bean的作用范围；
最后说到了AOP,简要的介绍了使用的场景，切入流程以及一个使用实例。




![image.png](https://cdn.nlark.com/yuque/0/2020/png/186661/1585994910076-3510a214-90e1-47b2-93c5-12cd08d0386e.png#align=left&display=inline&height=997&name=image.png&originHeight=1342&originWidth=1004&size=167703&status=done&style=none&width=746)

