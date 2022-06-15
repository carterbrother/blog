package org.example.mianshi;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 不常使用的资源被释放掉
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String,String>(){

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size()>3;
            }
        };

        linkedHashMap.put("a","aaa");
        linkedHashMap.put("b","bbb");
        linkedHashMap.put("c","ccc");


        linkedHashMap.forEach((k,v)->System.out.println(k+" = " + v));


        System.out.println(linkedHashMap.get("a"));
        System.out.println(linkedHashMap.get("b"));
        System.out.println(linkedHashMap.get("c"));

        linkedHashMap.forEach((k,v)->System.out.println(k+" = " + v));

        linkedHashMap.put("d","ddd");

        System.out.println("=========");

        linkedHashMap.forEach((k,v)->System.out.println(k+" = " + v));


    }
}
