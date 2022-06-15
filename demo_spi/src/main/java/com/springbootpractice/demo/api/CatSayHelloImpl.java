package com.springbootpractice.demo.api;

/**
 * @author carter
 * create_date  2020/5/26 14:11
 * description     TODO
 */

public class CatSayHelloImpl implements SayHelloI {
    @Override
    public void sayHell(String name) {
        System.out.println("cat say miao miao miao !");
    }
}
