package com.springbootpractice.demoevent.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 * @说明 吃饭事件
 * @作者 carter
 * @创建时间 2019年07月12日 13:12
 **/
public class EatEvent extends ApplicationEvent {

    private static final Logger logger = LoggerFactory.getLogger(EatEvent.class);

    private Boolean eatFinished;

    public EatEvent(Boolean eatFinished) {
        super(eatFinished);
        this.eatFinished = eatFinished;
    }

    public void callGirlFriend() {
        logger.info("美女,吃完饭了，来收拾一下吧！");
    }

    public void callBrothers() {
        logger.info("兄弟们，吃完饭了，来打dota ！");
    }

    public Boolean getEatFinished() {
        return eatFinished;
    }
}
