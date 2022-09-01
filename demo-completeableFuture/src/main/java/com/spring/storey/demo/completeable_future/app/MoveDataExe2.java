package com.spring.storey.demo.completeable_future.app;


import com.spring.storey.demo.completeable_future.client.dto.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoveDataExe2 {

    public R<String> execute(String appId) {

        String msg = "";
        try {
            Assert.hasText(appId, "appId不能为空");
            msg = moveData(appId).getData();

        } catch (Exception ex) {
            msg = String.valueOf(R.error(ex).getData());
        } finally {
            R r = R.success();
            r.setMsg(msg);
            return r;
        }
    }

    private R<String> moveData(String appId) {

        StopWatch stopWatch = new StopWatch("moveData");

        stopWatch.start("getWsByAppId");
        List<String> wsList = getWsByAppId(appId);
        stopWatch.stop();


        stopWatch.start("dataXMoveData");
        wsList.parallelStream().forEach(this::dataXMoveData);
        stopWatch.stop();

        stopWatch.start("transRow");
        wsList.parallelStream().forEach(this::transRow);
        stopWatch.stop();

        stopWatch.start("updateWsCount");
        wsList.parallelStream().forEach(this::updateWsCount);
        stopWatch.stop();

        log.info("{},totalSecond:{}", stopWatch.prettyPrint(), stopWatch.getTotalTimeSeconds());
        return R.success(appId);

    }

    private void updateWsCount(String ws) {

        log.info("开始处理updateWsCount任务:{}", ws);

        long executeTime = 1;


        try {
            TimeUnit.SECONDS.sleep(executeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("完成处理updateWsCount任务:{}", ws);
    }

    private void transRow(String ws) {

        log.info("开始处理transRow任务:{}", ws);

        long executeTime = 15;
        switch (ws) {
            case "task9":
            case "task8":
            case "task10": {
                executeTime = 5 * 3;
                break;
            }
            case "task50": {
                executeTime = 2;
                break;
            }
            case "task30": {
                executeTime = 4 * 2;
                break;
            }
            default: {
                break;
            }

        }

        try {
            TimeUnit.SECONDS.sleep(executeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("完成处理transRow任务:{}", ws);

    }

    private void dataXMoveData(String ws) {

        log.info("开始处理dataXMoveData任务:{}", ws);

        long executeTime = 1;
        switch (ws) {
            case "task9":
            case "task8":
            case "task10": {
                executeTime = 5;
                break;
            }
            case "task5": {
                executeTime = 2;
                break;
            }
            case "task3": {
                executeTime = 4;
                break;
            }
            default: {
                break;
            }

        }

        try {
            TimeUnit.SECONDS.sleep(executeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("完成处理dataXMoveData任务:{}", ws);

    }

    //模拟得到100个工作表

    private List<String> getWsByAppId(String appId) {
        return IntStream.rangeClosed(1, 10).mapToObj(i -> "task".concat(String.valueOf(i))).collect(Collectors.toList());
    }


}
