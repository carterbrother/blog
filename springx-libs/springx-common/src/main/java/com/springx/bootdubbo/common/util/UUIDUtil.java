package com.springx.bootdubbo.common.util;

import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;

/**
 * UUID工具类
 *
 * @author Grit
 */
public final class UUIDUtil {
    private static final Random RANDOM = new Random();

    private UUIDUtil() {
    }

    /**
     * 随机生成UUID，不含“-”，长度32
     *
     * @return 随机生成的UUID字符串，不含“-”，长度32
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static byte[] nextBytes(int count) {
        Assert.isTrue(count >= 0, String.format("%s Count cannot be negative.", String.valueOf(count)));
        byte[] result = new byte[count];
        RANDOM.nextBytes(result);
        return result;
    }

    public static int nextInt(int startInclusive, int endExclusive) {

        Assert.isTrue(startInclusive >= 0, String.format("%s Both range values must be non-negative..", String.valueOf(startInclusive)));
        Assert.isTrue(endExclusive >= startInclusive, String.format("%s Start value must be smaller or equal to end value.", String.valueOf(startInclusive)));
        return startInclusive == endExclusive ? startInclusive : startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    public static long nextLong(long startInclusive, long endExclusive) {
        Assert.isTrue(startInclusive >= 0, String.format("%s Both range values must be non-negative..", String.valueOf(startInclusive)));
        Assert.isTrue(endExclusive >= startInclusive, String.format("%s Start value must be smaller or equal to end value.", String.valueOf(startInclusive)));
        return startInclusive == endExclusive ? startInclusive : (long) nextDouble((double) startInclusive, (double) endExclusive);
    }

    public static double nextDouble(double startInclusive, double endInclusive) {
        Assert.isTrue(startInclusive >= 0.0f, String.format("%s Both range values must be non-negative..", String.valueOf(startInclusive)));
        Assert.isTrue(endInclusive >= startInclusive, String.format("%s Start value must be smaller or equal to end value.", String.valueOf(startInclusive)));
        return startInclusive == endInclusive ? startInclusive : startInclusive + (endInclusive - startInclusive) * RANDOM.nextDouble();
    }

    public static float nextFloat(float startInclusive, float endInclusive) {
        Assert.isTrue(startInclusive >= 0.0f, String.format("%s Both range values must be non-negative..", String.valueOf(startInclusive)));
        Assert.isTrue(endInclusive >= startInclusive, String.format("%s Start value must be smaller or equal to end value.", String.valueOf(startInclusive)));
        return startInclusive == endInclusive ? startInclusive : startInclusive + (endInclusive - startInclusive) * RANDOM.nextFloat();
    }
}