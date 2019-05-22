package com.springx.bootdubbo.common.enums;

import com.springx.bootdubbo.common.enums.kafka.RpmWarningKafkaBean;
import org.apache.commons.lang3.StringUtils;

/**
 *  kafka主题
 *
 * @author <a href="mailto:505847426@qq.com">carterbrother</a>
 * @description <br>
 * @date 2015年12月23日
 * @Copyright (c) 2015, springx.com
 */
public enum KafkaTopicEnum implements BaseEnum<String> {

    RPM预警消息("_rpm_warning_topic", RpmWarningKafkaBean.class);


    private final String topic;
    private Class messageDtoClass;

    KafkaTopicEnum(String topic, Class messageDtoClass) {
        this.topic = topic;
        this.messageDtoClass = messageDtoClass;
    }

    @Override
    public String code() {
        return this.topic;
    }

    public static KafkaTopicEnum getByCode(String code) {
        KafkaTopicEnum enums[] = KafkaTopicEnum.values();
        for (KafkaTopicEnum openAccountType : enums) {
            if (StringUtils.equals(openAccountType.topic, code)) {
                return openAccountType;
            }
        }
        return null;
    }

    public Class getMessageDtoClass() {
        return messageDtoClass;
    }
}
