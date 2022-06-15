//package com.xxx.demo.config;
//
//import com.lifesense.ihs.saasuser.biz.ConfigBiz;
//import com.lifesense.ihs.saasuser.constant.Constants;
//import com.lifesense.kafka.spring.TopicPublisherBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
///**
// * 说明：kafka配置
// * @author 李福春
// * 创建时间： 2019年11月29日 16:02
// **/
//@Configuration
//public class KafkaConfig {
//
//
//    @Bean
//    public TopicPublisherBean topicPublisherBean(){
//        TopicPublisherBean topicPublisherBean = new TopicPublisherBean();
//        topicPublisherBean.setDefaultAsynSend(true);
//        final Properties configs = new Properties();
//
//        configs.put("bootstrap.servers", ConfigBiz.getKafkaBootStrapServers());
//        configs.put("acks", ConfigBiz.getKafkaAcks());
//        configs.put("retries", ConfigBiz.getKafkaRetries());
//        configs.put("compression.type", ConfigBiz.getKafkaCompressionType());
//        configs.put("client.id", Constants.APP_NAME.concat("-kafka-producer"));
//
//        topicPublisherBean.setConfigs(configs);
//        return topicPublisherBean;
//    }
//
//}
