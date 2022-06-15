import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * 说明：成功执行结果汇集
 * @author carter
 * 创建时间： 2020年03月19日 10:34 上午
 **/
@Slf4j
public class Test3 {

    public static void main(String[] args) {

        List<ListenableFuture<QueryResult>> querys = Lists.newLinkedList();
        final ListenableFuture<List<QueryResult>> successfulAsList = Futures.successfulAsList(querys);

        Futures.addCallback(successfulAsList, new FutureCallback<List<QueryResult>>() {
            @Override
            public void onSuccess(List<QueryResult> queryResults) {
                log.info("执行结果列表：{}",queryResults);
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.error("执行失败",throwable);
            }
        }, Executors.newFixedThreadPool(2));


    }

    @Data
    @AllArgsConstructor
    public static class QueryResult{


      private  Integer age;

    }


}
