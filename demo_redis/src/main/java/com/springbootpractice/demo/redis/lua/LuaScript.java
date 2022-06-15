package com.springbootpractice.demo.redis.lua;

/**
 * 说明：TODO
 * @author carter
 * 创建时间： 2020年01月21日 6:28 下午
 **/

public final class LuaScript {

    public static final String lua1="redis.call('set',KEYS[1],ARGV[1])\n" +
            "redis.call('set',KEYS[2],ARGV[2])\n" +
            "local str1=redis.call('get',KEYS[1])\n" +
            "local str2=redis.call('get',KEYS[2])\n" +
            "if str1 == str2 then\n" +
            "return 1\n" +
            "else\n" +
            "return 0";

}
