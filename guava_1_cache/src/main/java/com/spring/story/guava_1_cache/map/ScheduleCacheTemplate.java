package com.spring.story.guava_1_cache.map;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class ScheduleCacheTemplate<T> {


    private static final Map<String, Cache> cacheMap = new ConcurrentHashMap<>(8);

    /**
     * 获取资源
     *
     * @param <T>          资源类型由调用方自己决定
     * @param initSupplier 资源初始化函数由调用方自己决定
     * @param keepSeconds  资源在缓存中保存的时间，单位秒
     * @return 资源
     */
    public <T> T getResource(Supplier<T> initSupplier, Supplier<String> keySupplier, Long keepSeconds, Consumer<T> closeResourceConsumer) {
        String resourceKey = keySupplier.get();
        Cache<String, T> cache;
        if (!cacheMap.containsKey(resourceKey)) {
            cache = CacheBuilder.newBuilder()
                    .initialCapacity(1).maximumSize(1).recordStats()
                    .expireAfterAccess(keepSeconds, TimeUnit.SECONDS)
                    .removalListener((RemovalListener<String, T>) notification -> {
                        log.info("cache time reach , close Cache");
                        closeResourceConsumer.accept(notification.getValue());
                    }).build();

            cacheMap.putIfAbsent(resourceKey, cache);
        } else {
            cache = cacheMap.get(resourceKey);
        }

        try {
            return cache.get(resourceKey, new Callable<T>() {
                @Override
                public T call() throws Exception {
                    log.info("cache not exist , init {}", resourceKey);
                    return getT(resourceKey, initSupplier);


                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


    }

    private <T> T getT(String resourceKey, Supplier<T> initSupplier) {
        synchronized (resourceKey) {
            return initSupplier.get();

        }
    }


}
