package org.example.mianshi.classloader;



import java.util.Collection;

/**
 * 作者:     carter
 * 创建日期:  2020/3/31 12:41
 * 描述:     类加载器的层级关系
 */

public class PrintClassLoaderApp {


    public static void main(String[] args) {

        System.out.println("PrintClassLoaderApp 的类加载器是："+PrintClassLoaderApp.class.getClassLoader());
        System.out.println("parent 的类加载器是："+ PrintClassLoaderApp.class.getClassLoader().getParent());
        System.out.println("parent.parent 的类加载器是："+ PrintClassLoaderApp.class.getClassLoader().getParent().getParent());
        System.out.println("Collection 的类加载器是："+ Collection.class.getClassLoader());

    }

}
