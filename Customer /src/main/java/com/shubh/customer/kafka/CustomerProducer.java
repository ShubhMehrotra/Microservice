package com.shubh.customer.kafka;

import com.shubh.customer.event.CustomerCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomerProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerProducer.class);
    private final NewTopic topic;
    private final KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate;

    public CustomerProducer(KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate, NewTopic topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void sendCustomerCreatedEvent(CustomerCreatedEvent event) {
        // Logging the event info
        LOGGER.info("Customer Created Event => {}", event);

        Long userId = event.getUserId();
        if (userId == null) {
            // If userId is null, log an error and exit early
            LOGGER.error("UserId is empty. Cannot send CustomerCreatedEvent to Kafka.");
            return;
        }

        try {
            // Create the Kafka message to send
            Message<CustomerCreatedEvent> message = MessageBuilder
                    .withPayload(event)
                    .setHeader(KafkaHeaders.TOPIC, topic.name()) // Set the topic to which the message is sent
                    .build();

            // Send the message to Kafka
            kafkaTemplate.send(message);

            // Log the successful sending of the event
            LOGGER.info("Sent CustomerCreatedEvent to Kafka topic: {}", topic.name());

        } catch (Exception e) {
            // Handle errors when sending the event to Kafka
            LOGGER.error("Error sending CustomerCreatedEvent to Kafka", e);
        }
    }
}
