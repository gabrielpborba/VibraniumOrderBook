package br.com.orderbookmanager.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TradeQueueProducer {

    @Value("${rabbitmq.trade-exchange.name}")
    private String exchange;

    @Value("${rabbitmq.trade-routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(TradeQueueProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public TradeQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        try {
            LOGGER.info(String.format("Message sent -> %s", message));
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}