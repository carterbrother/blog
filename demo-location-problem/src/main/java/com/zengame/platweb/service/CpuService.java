package com.zengame.platweb.service;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  19:46
 **/
@Service
public class CpuService {

    private static String str = IntStream.rangeClosed(1,10000).mapToObj(x->"A").collect(Collectors.joining(""));
    @Async
    public void cpu(){
        while (true){
            IntStream.rangeClosed(1,10000000).parallel().forEach(i-> DigestUtil.md5Hex(str));
        }
    }


}
