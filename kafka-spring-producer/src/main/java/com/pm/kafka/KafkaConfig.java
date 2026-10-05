package com.pm.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${spring.kafka.bootstrap_servers:localhost}")
    private String kafkaHost;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        logger.info("Bootstrap server url : {}", kafkaHost);
        Map<String,Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost+":9092");
        return new KafkaAdmin(configs);
    }

//    @Bean
//    public NewTopic topic() {
//        return new NewTopic("test-topic", 1, (short) 1);
//    }

}
