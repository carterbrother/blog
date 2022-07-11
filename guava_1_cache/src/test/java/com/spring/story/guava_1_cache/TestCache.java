package com.spring.story.guava_1_cache;

import com.spring.story.guava_1_cache.cache.ResourceCacheTemplate;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


/**
 * @author Carter.li
 * @createtime 2022/7/8 11:04
 */
public class TestCache {

    @Test
    public void testGuavaCache(){

        ResourceCacheTemplate<String> cacheTemplate = new ResourceCacheTemplate<>();


        for (int i=1;i<=5;i++){



        cacheTemplate.getResource(() -> "pool", () -> "testpool", 5L, b -> {
            System.out.println("close "+ b);
        });


        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        }

    }

}
