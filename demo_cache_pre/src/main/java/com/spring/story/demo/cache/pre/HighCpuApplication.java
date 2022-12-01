package com.spring.story.demo.cache.pre;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CPU问题定位
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/28  16:28
 **/

@SpringBootApplication
public class HighCpuApplication {

    public static final Random RANDOM = new Random();
    private static String payload = IntStream.rangeClosed(1,10000).mapToObj(x->"A").collect(Collectors.joining(""));

    public static void main(String[] args) {
        HighCpuApplication.task();
    }

    private static void task() {
        do{
            doTask(RANDOM.nextInt(100));
        }while (true);
    }

    private static void doTask(int i) {
        if (i == 1){
            IntStream.rangeClosed(1,10000).parallel().forEach(j-> DigestUtil.md5Hex(payload));
        }
    }

}
