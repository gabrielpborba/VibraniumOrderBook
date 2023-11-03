package br.com.trademanager.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserQueueProducer {

    @Value("${rabbitmq.user-exchange.name}")
    private String exchange;

    @Value("${rabbitmq.user-routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(UserQueueProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public UserQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            logger.info("Message Sent to user-queue: ".concat(message));
        }catch (Exception e){
            logger.error("Failed to send message to user-queue ". concat(message));
        }

    }
}