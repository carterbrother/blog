package com.spring.story.guava_1_cache.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j
public class ResourceCacheTemplate<T> {


    private static final Map<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
    private static final ScheduledExecutorService scheduledPool = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        thread.setName("_resource".concat(UUID.randomUUID().toString()));
        return thread;
    });


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
                    .expireAfterWrite(keepSeconds, TimeUnit.SECONDS)
                    .build();

            T t = getT(resourceKey, initSupplier);
            cache.put(resourceKey, t);

            cacheMap.putIfAbsent(resourceKey, cache);

            scheduledPool.scheduleAtFixedRate(() -> {
                if (Objects.isNull(cache.getIfPresent(resourceKey)) && cacheMap.containsKey(resourceKey)) {
                    log.info("cache time 【{}】 reach , close cache key:【{}】", keepSeconds, resourceKey);
                    closeResourceConsumer.accept(t);
                    cacheMap.remove(resourceKey);
                }

            }, 0L, 1L, TimeUnit.SECONDS);

        } else {
            cache = cacheMap.get(resourceKey);
        }

        return cache.getIfPresent(resourceKey);


    }

    private <T> T getT(String resourceKey, Supplier<T> initSupplier) {
        synchronized (resourceKey) {
            log.info("init resource key 【{}】 ", resourceKey);
            return initSupplier.get();

        }
    }


}
