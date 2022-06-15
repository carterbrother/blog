import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * 说明：嵌套的ListenableFuture
 * @author carter
 * 创建时间： 2020年03月19日 10:43 上午
 **/

public class Test4 {

    public static void main(String[] args) {


        final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        final ListeningExecutorService otherExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));


        Callable<Foo> otherCallback =  ()->new Foo("aaa");


        final ListenableFuture<ListenableFuture<Foo>> submit =
                executorService.submit(() -> otherExecutorService.submit(otherCallback));


    }

    @Data
    @AllArgsConstructor
    public static class Foo{

        private String name;
    }

}
