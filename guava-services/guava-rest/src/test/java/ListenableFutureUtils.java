import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 说明：使用例子代码
 * @author carter
 * 创建时间： 2020年03月19日 9:54 上午
 **/

@Slf4j
public class ListenableFutureUtils {

    public static void main(String[] args) {

        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


        final ListenableFuture<AResult> listenableFuture = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new AResult(30, "male", 1);

        });


        Futures.addCallback(listenableFuture,
                new FutureCallback<AResult>() {
                    @Override
                    public void onSuccess(AResult aResult) {
                        log.info("计算成功，{}",aResult);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {

                        log.error("计算错误",throwable);

                    }
                },service);

    }

    @Data
    @AllArgsConstructor
    public static class AResult{

        private Integer age;

        private String sex;

        private Integer id;


    }

}
