package com.shubh.cart.Cart.kafka;

import com.shubh.customer.event.CustomerCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class CustomerConsumer {
    private static  final Logger LOGGER= LoggerFactory.getLogger(CustomerConsumer.class);

    @KafkaListener(topics="${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CustomerCreatedEvent event)
    {
        LOGGER.info(String.format("Order Event received in stock service is => %s", event.toString()));

        //Send the Order email to customer (Later impl)

    }
}
