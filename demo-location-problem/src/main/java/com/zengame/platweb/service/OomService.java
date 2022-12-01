package com.zengame.platweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 20个线程，
 *
 * @author Carter.li
 * {@code @createdAt:}: 2022/11/30  14:43
 **/
@Service
@Slf4j
public class OomService {

    private List<String> dataList = new CopyOnWriteArrayList<>();


    @Async
    public void oom(int threadNum, int objNum) {
        log.info("====start make big obj===");
        IntStream.rangeClosed(1, Optional.of(threadNum).filter(item->item>=10).orElse(10))
                .mapToObj(i -> new Thread(() -> IntStream.rangeClosed(1, Optional.of(objNum).filter(item->item>=1000).orElse(1000))
                        .forEach(x -> {
                            String str = IntStream.rangeClosed(1, 10000000).mapToObj(y -> "ZenGame").collect(Collectors.joining("-"));
                            dataList.add(str);
                            try {
                                TimeUnit.MILLISECONDS.sleep(5);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        })))
                .parallel().forEach(Thread::start);
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
