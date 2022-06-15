package com.springpractice._var;

import lombok.var;

import java.awt.*;

/**
 * 说明：var的使用
 * @author carter
 * 创建时间： 2019年10月10日 20:08
 **/

public class App {

    public static void main(String[] args) {

        var x = "Hello";

        //编译报错，类型是String
//        x=1;

        var y = Color.RED;

//        y=x;

        //var的类型如果是object ,则不是它的使用场景
        var z = new Object();

        z = x;

        System.out.println(x);

    }

}
