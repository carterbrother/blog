import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Executors;

/**
 * 说明：异步操作链
 * @author carter
 * 创建时间： 2020年03月19日 10:11 上午
 **/

public class ApplicationUtils {


    public static void main(String[] args) {

        Query query = new Query(30);

        ListenableFuture<RowKey> rowKeyFuture = lookUp(query);

        AsyncFunction<RowKey, QueryResult> queryFun = rowKey -> readData(rowKey);

        final ListenableFuture<QueryResult> queryResultListenableFuture = Futures.transformAsync(rowKeyFuture, queryFun, Executors.newFixedThreadPool(1));

    }

    private static ListenableFuture<QueryResult> readData(RowKey rowKey) {
        return null;
    }

    private static ListenableFuture<RowKey> lookUp(Query query) {
        return null;
    }


    @Data
    @AllArgsConstructor
    public static class RowKey {

        private String id;

    }

    @Data
    @AllArgsConstructor
    public static class Query {

        private Integer age;

    }


    @Data
    @AllArgsConstructor
    public static class QueryResult {

        private String id;
        private String age;

    }


}
