package com.springpractice._val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    public String test1() {
        final ArrayList<String> exampleList = new ArrayList<>();
        exampleList.add("hello world");

        final String firstElement = exampleList.get(0);
        final String returnString = firstElement.toLowerCase();
        System.out.println(returnString);
        return returnString;
    }

    public void test2() {

        final HashMap<Integer, String> map = new HashMap<>();
        map.put(0, "zero");
        map.put(5, "five");

        for (final Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.printf("%d: %s \n", entry.getKey(), entry.getValue());
        }

    }


    public static void main(String[] args) {
        final App app = new App();
        app.test1();
        System.out.println("======");
        app.test2();

    }

}
