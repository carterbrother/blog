package com.carter.demo.classload.pay;


import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author relax
 * @version 1.0
 * @since jdk1.7, Created on 2016-12-12 下午12:06:09
 **/
@Slf4j
public class PayFactory {

    private static final class Holder {
        public static PayFactory module = new PayFactory();
    }

    public static PayFactory getInstance() {
        return Holder.module;
    }


    ReentrantLock lock = new ReentrantLock();

    static ReentrantLock lock2 = new ReentrantLock();

    private Map<String, BasePay> codes = new ConcurrentHashMap<>();

    //支付代码数据

    List<PayConfig> configs;

    //所有pay相关的class对象

    private Map<String, BasePay> payClasses = new ConcurrentHashMap<>();

    /**
     * 缓存数据更新时执行
     * @param configs
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void init(List<PayConfig> configs) throws InstantiationException, IllegalAccessException {

        //找出不同的数据进行更新

        if (configs != null && BeanManager.getContext() != null) {
            parsePayConfig();
        }

        this.configs = configs;
    }

    /**
     * 初始化pay实现类
     */
    final String path = "com.zengame.platform.pay.common.pay";

    private void classInit() {
        if (payClasses.size() > 0) return;
        List<Class<?>> clzs = ClassUtil.getClasses(path);
        for (Class<?> clz : clzs) {

            Annotation[] anns = clz.getAnnotations();
            for (Annotation annt : anns) {
                if (annt instanceof PayCodeDesc) {
                    PayCodeDesc ann = (PayCodeDesc) annt;
                    BasePay pay = null;
                    try {
                        pay = (BasePay) clz.newInstance();
                    } catch (Exception e) {
                        log.error("pay class init error,uri:" + ann.uri(), e);
                    }
                    String uri = ann.uri();
                    String codeName = uri.split("\\/")[1];
                    payClasses.put(codeName, pay);
                }
            }

        }

    }

    /**
     * spring加载完成后执行
     */
    public void parsePayConfig() throws InstantiationException, IllegalAccessException {
        if (configs == null || configs.size() == 0) {
            return;
        }

        //初始化pay实现类
        classInit();

        //初始实现类配置
        lock.lock();
        try {
            Collection<BasePay> items = payClasses.values();
            for (BasePay pay : items) {
                register(pay);
            }
        } finally {
            lock.unlock();
        }

    }

    /**
     * 注册pay的实现类
     * @param pay
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void register(BasePay pay) throws InstantiationException, IllegalAccessException {
        Annotation[] anns = pay.getClass().getAnnotations();
        for (Annotation annt : anns) {
            if (annt instanceof PayCodeDesc) {
                PayCodeDesc ann = (PayCodeDesc) annt;
                String uri = ann.uri();
                String codeName = uri.split("\\/")[1];
                codes.put(uri, pay);
                List<PayConfig> items = findConfigsByName(codeName, configs);
                for (PayConfig config : items) {
                    codes.put(String.valueOf(config.getPayType()), pay);
                }
            }
        }
    }

    public void classChange(List<String> changes) {
        if (changes == null || changes.size() == 0) {
            return;
        }
        //更新总线类，保证下次paylist 变更是不被遗漏或者覆盖
        //对类进行更新
        lock.lock();
        try {
            for (String className : changes) {
                Class<?> clz = getInstance().getClass(className);
                Annotation[] anns = clz.getAnnotations();
                for (Annotation annt : anns) {
                    if (annt instanceof PayCodeDesc) {

                        PayCodeDesc ann = (PayCodeDesc) annt;
                        String uri = ann.uri();

                        BasePay pay = null;
                        try {
                            pay = (BasePay) clz.newInstance();
                        } catch (Exception e) {
                            log.error("pay class init error,uri:" + ann.uri(), e);
                        }

                        String codeName = uri.split("\\/")[1];
                        //移除
                        if (payClasses.containsKey(codeName)) {
                            payClasses.remove(codeName);
                        }

                        payClasses.put(codeName, pay);
                        codes.put(uri, pay);
                        List<PayConfig> items = findConfigsByName(codeName, configs);
                        for (PayConfig config : items) {
                            codes.put(String.valueOf(config.getPayType()), pay);
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }


    private List<PayConfig> findConfigsByName(String name, List<PayConfig> configs) {
        List<PayConfig> items = new ArrayList<PayConfig>();
        for (PayConfig config : configs) {
            if (name.equals(config.getName())) {
                items.add(config);
            }
        }
        return items;
    }

    /**
     * 更加payCode获取支付类型实现
     *
     * @param payCode
     * @return
     */
    public BasePay getPay(int payCode) {
        return this.getPay(String.valueOf(payCode));
    }

    /**
     * 更加uri获取支付类型实现
     *
     * @param uri
     * @return
     */
    public BasePay getPay(String uri) {
        if (!codes.isEmpty() && codes.containsKey(uri)) {
            return codes.get(uri);
        }
        if(lock2.tryLock()) {
            try {
                log.info("===start lazy init payFactory");
                try {
                    parsePayConfig();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                return codes.get(uri);
            }finally {
               lock2.unlock();
           }
        }else{
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return getPay(uri);
        }




    }
}