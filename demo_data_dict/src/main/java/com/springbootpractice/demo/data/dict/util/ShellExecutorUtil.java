package com.springbootpractice.demo.data.dict.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author carter
 * create_date  2020/5/14 15:13
 * description     shell执行工具类
 */
@Slf4j
public class ShellExecutorUtil {

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final int CORE_POOL_SIZE = AVAILABLE_PROCESSORS * 2 ;
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE,
            CORE_POOL_SIZE,
            300, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2000),
            new ThreadFactoryBuilder().setDaemon(true).setNameFormat("datax-exec-").build(),
            new ThreadPoolExecutor.AbortPolicy());


    /**
     * 执行shell指令
     *
     * @param shCmd shell指令
     * @param block 是否需要等待结果执行完毕
     */
    public static Future<AtomicInteger> executeShell(String shCmd, Boolean block) {

        Future<AtomicInteger> future = THREAD_POOL.submit(new CommandWaitForThread(shCmd));

        if (!block) {
            return future;
        }

        int exitValue;
        try {
            exitValue = future.get(15, TimeUnit.MINUTES).get();

            if (exitValue != 0) {
                log.error("shell脚本：{} 执行失败: {}", shCmd, exitValue);
            }

            log.info("shell脚本：{} 执行成功。", shCmd);

        } catch (InterruptedException e) {
            log.error("shell脚本：{} 执行被打断", shCmd, e);
        } catch (ExecutionException e) {
            log.error("shell脚本：{} 执行异常", shCmd, e);
        } catch (TimeoutException e) {
            log.error("shell脚本：{} 执行超时", shCmd, e);
        }

        return future;

    }

    private static class CommandWaitForThread implements Callable<AtomicInteger> {


        private final String cmd;

        private final AtomicInteger exitValue = new AtomicInteger(-1);


        public CommandWaitForThread(String cmd) {
            this.cmd = cmd;
        }

        @Override
        public AtomicInteger call() throws Exception {

            BufferedReader bufferedInfo = null;
            BufferedReader bufferedErr = null;

            try {
                Process process = Runtime.getRuntime().exec(cmd);

                bufferedInfo = new BufferedReader(new InputStreamReader(process.getInputStream()));
                bufferedErr = new BufferedReader(new InputStreamReader(process.getErrorStream()));

                String line;
                String costSecond = "";
                String dataCount = "";
                while (Objects.nonNull(line = bufferedInfo.readLine())) {
                    if (line.contains("任务总计耗时")){
                        costSecond = line.trim().replaceAll(" ", "");
                    }
                    if(line.contains("读出记录总数")){
                        dataCount= line.trim().replaceAll(" ", "");
                    }
                }

                while (Objects.nonNull(line = bufferedErr.readLine())) {
                    log.error("{}", line);
                }

                //阻塞执行线程直至脚本执行完成后返回
                exitValue.set(process.waitFor());

                log.info("{}-{}-{}-处理{}!",cmd, dataCount ,costSecond,exitValue.get()==0?"成功":"失败");

            } catch (IOException e) {
                e.printStackTrace();
            } finally {


                if (Objects.nonNull(bufferedErr)) {
                    try {
                        bufferedErr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        bufferedInfo.close();
                    }
                }
            }

            return exitValue;
        }
    }


    public static void closePool() {
        log.info("开始关闭线程池,活跃任务数量：{}", THREAD_POOL.getActiveCount());
        THREAD_POOL.shutdown();
    }

}
