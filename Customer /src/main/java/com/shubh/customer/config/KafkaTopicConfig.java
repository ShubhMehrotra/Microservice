package com.shubh.customer.config;

import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    //Spring bean for Kafka Topic
    @Bean
    public NewTopic topic()
    {
        return TopicBuilder.name(topicName)
                .build();
    }
}
