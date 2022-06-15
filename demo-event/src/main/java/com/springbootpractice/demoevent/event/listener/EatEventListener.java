package com.springbootpractice.demoevent.event.listener;

import com.springbootpractice.demoevent.event.EatEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 说明: XEvent的事件监听器
 *
 * @author carter
 * 创建时间 2019年07月12日 13:19
 **/
@Component
public class EatEventListener implements ApplicationListener<EatEvent> {

    private static final Logger logger = LoggerFactory.getLogger(EatEventListener.class);

    @Override
    public void onApplicationEvent(EatEvent xEvent) {
        if (Objects.isNull(xEvent)) {
            return;
        }

        if (xEvent.getEatFinished()) {
            xEvent.callGirlFriend();
            logger.info("xxxx,女朋友拒绝收拾！");
            xEvent.callBrothers();
            logger.info("满人了，下次带你！");
        }

    }
}
