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

    // Spring bean for Kafka Topic with partitions and replicas
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .partitions(3)  // ✅ Improve parallel processing
                .replicas(1)    // ✅ Adjust based on Kafka cluster
                .build();
    }

    // Dead Letter Queue (DLQ) for handling failures
    @Bean
    public NewTopic deadLetterTopic() {
        return TopicBuilder.name(topicName + "_DLQ")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
