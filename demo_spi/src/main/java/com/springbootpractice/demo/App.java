package com.springbootpractice.demo;

import com.springbootpractice.demo.api.SayHelloI;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<SayHelloI> serviceLoader = ServiceLoader.load(SayHelloI.class);
        serviceLoader.iterator().forEachRemaining(sayHelloI -> sayHelloI.sayHell("aaa"));
    }
}
