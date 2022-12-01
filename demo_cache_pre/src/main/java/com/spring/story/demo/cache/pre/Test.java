package com.spring.story.demo.cache.pre;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/27  12:17
 **/
public class Test {

    public static void main(String[] args) {

        printJvmInfo(args);

        IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
            while (true) {

                String s = IntStream.rangeClosed(1, 1000000).mapToObj(x -> "a").collect(Collectors.joining("")) + UUID.randomUUID();

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(s.length());


            }
        })).forEach(Thread::start);

        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void printJvmInfo(String[] args){

        System.out.println("VM options");
        System.out.println(ManagementFactory.getRuntimeMXBean().getInputArguments().stream().collect(Collectors.joining(System.lineSeparator())));
        System.out.println("Program arguments");
        System.out.println(Arrays.stream(args).collect(Collectors.joining(System.lineSeparator())));
    }

}
