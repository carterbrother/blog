import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.swing.event.ChangeEvent;

/**
 * 说明：event bus
 * @author carter
 * 创建时间： 2020年03月20日 9:50 上午
 **/
@Slf4j
public class Test5 {


    public static void main(String[] args) {


        EventBus eventBus =  new EventBus("aaa");

        eventBus.register(new EventListener());

        eventBus.post(new MyEvent("hello"));
        eventBus.post(new MyEvent("world"));
        eventBus.post(new MyEvent("carter"));


    }


    public static class EventListener{
        @Subscribe
        public void recordChange(MyEvent myEvent){
            log.info("{} , receive msg: {}",Thread.currentThread().getName(), myEvent);
        }

    }

    @Data
    @AllArgsConstructor
    public static class MyEvent{

        private String msg;

    }


}
