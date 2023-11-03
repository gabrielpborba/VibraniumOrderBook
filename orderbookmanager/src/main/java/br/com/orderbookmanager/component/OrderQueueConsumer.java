package br.com.orderbookmanager.component;

import br.com.orderbookmanager.dto.OrderDto;
import br.com.orderbookmanager.model.OrderEntity;
import br.com.orderbookmanager.repository.OrderRepository;
import br.com.orderbookmanager.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderQueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderQueueConsumer.class);

    @Autowired
    private OrderService orderService;


    @RabbitListener(queues = {"${rabbitmq.order-queue.name}"})
    public void consume(String message) {
        logger.info("Received message from order-queue: " + message);

        try {
            OrderDto orderDto = parseOrderDtoFromMessage(message);
            saveOrder(orderDto.getOrder());
            orderService.matchOrders(orderDto.getOrder());
        } catch (JsonProcessingException e) {
            logger.error("Error processing message: " + e.getMessage());
        }
    }

    private OrderDto parseOrderDtoFromMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(message, OrderDto.class);
    }

    private void saveOrder(OrderEntity orderEntity) {
        orderService.save(orderEntity);
    }
}

