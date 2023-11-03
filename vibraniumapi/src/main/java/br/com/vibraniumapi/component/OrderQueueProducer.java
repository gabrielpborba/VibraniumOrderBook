package br.com.vibraniumapi.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderQueueProducer {

    @Value("${rabbitmq.order-exchange.name}")
    private String exchange;

    @Value("${rabbitmq.order-routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(OrderQueueProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public OrderQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            logger.info("Message Sent to order-queue: ".concat(message));
        }catch (Exception e){
            logger.error("Failed to send messa to order-queue ". concat(message));
        }

    }
}