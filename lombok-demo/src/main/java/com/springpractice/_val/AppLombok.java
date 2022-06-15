package com.springpractice._val;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Hello world!
 */
public class AppLombok {

    //不能使用在类的成员变量上，会报编译错误
    // val String name;

    public String test1() {
        val exampleList = new ArrayList<String>();
        exampleList.add("hello world");

        val firstElement = exampleList.get(0);

        final String returnString = firstElement.toLowerCase();
        System.out.println(returnString);
        return returnString;
    }

    public void test2() {

        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");

        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s \n", entry.getKey(), entry.getValue());
        }

    }


    public static void main(String[] args) {
        final AppLombok app = new AppLombok();
        app.test1();
        System.out.println("======");
        app.test2();

    }
}
