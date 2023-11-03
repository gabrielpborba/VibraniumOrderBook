package br.com.vibraniumapi.component;


import br.com.vibraniumapi.dto.TradeDto;
import br.com.vibraniumapi.model.UserEntity;
import br.com.vibraniumapi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(UserQueueConsumer.class);


    private final UserService userService;

    @Autowired
    private EntityManager entityManager;


    @Autowired
    public UserQueueConsumer(UserService orderService) {
        this.userService = orderService;

    }

    @RabbitListener(queues = {"${rabbitmq.user-queue.name}"})
    public void consume(String message) {
        logger.info("Received message from user-queue: " + message);

        try {
            TradeDto tradeDto = parseTradeDtoFromMessage(message);

            updateUserForTrade(tradeDto.getIdBuyer(), tradeDto.getPrice(), tradeDto.getQuantity(), true);
            updateUserForTrade(tradeDto.getIdSeller(), tradeDto.getPrice(), tradeDto.getQuantity(), false);
        } catch (JsonProcessingException e) {
            logger.error("Error processing message: " + e.getMessage());
        }
    }

    private TradeDto parseTradeDtoFromMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return objectMapper.readValue(message, TradeDto.class);
    }

    private void updateUserForTrade(Long userId, double price, int quantity, boolean isBuyer) {
        Optional<UserEntity> user = userService.findById(userId);

        if (user.isPresent()) {
            double amountChange = price * quantity;
            double newAmount = isBuyer ? user.get().getAmount() - amountChange : user.get().getAmount() + amountChange;
            int newQuantity = isBuyer ? user.get().getQuantity() + quantity : user.get().getQuantity() - quantity;

            user.get().setAmount(newAmount);
            user.get().setQuantity(newQuantity);

            userService.create(user.get());
        }
    }
}

