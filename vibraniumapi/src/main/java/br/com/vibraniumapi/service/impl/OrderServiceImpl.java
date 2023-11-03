package br.com.vibraniumapi.service.impl;

import br.com.vibraniumapi.component.OrderQueueProducer;
import br.com.vibraniumapi.exceptions.InsufficientBalanceException;
import br.com.vibraniumapi.exceptions.InsufficientVibraniumException;
import br.com.vibraniumapi.dto.OrderMessageDto;
import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import br.com.vibraniumapi.model.UserEntity;
import br.com.vibraniumapi.service.OrderService;
import br.com.vibraniumapi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderQueueProducer orderQueueProducer;

    @Autowired
    private UserService userService;

    public void create(OrderEntity orderEntity) throws JsonProcessingException {
        double orderValue = orderEntity.getQuantity() * orderEntity.getPrice();
        UserEntity user = getUser(orderEntity.getUser().getId());

        validateOrder(orderEntity, user, orderValue);

        orderEntity.setInstant(Instant.now());
        orderEntity.setAvailable(true);

        OrderMessageDto orderMessageDTO = createOrderMessageDto(orderEntity);
        String message = convertOrderMessageDtoToString(orderMessageDTO);

        orderQueueProducer.sendMessage(message);
    }

    private UserEntity getUser(long userId) {
        Optional<UserEntity> userOptional = userService.findById(userId);
        return userOptional.orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
    }

    private void validateOrder(OrderEntity orderEntity, UserEntity user, double orderValue) {
        if (OrderType.BUY.equals(orderEntity.getType())) {
            if (user.getAmount() < orderValue) {
                throw new InsufficientBalanceException(user.getAmount(), orderValue);
            }
        } else if (OrderType.SELL.equals(orderEntity.getType())) {
            if (user.getQuantity() < orderEntity.getQuantity()) {
                throw new InsufficientVibraniumException(user.getQuantity(), orderEntity.getQuantity());
            }
        }
    }

    private OrderMessageDto createOrderMessageDto(OrderEntity orderEntity) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return new OrderMessageDto(orderEntity);
    }

    private String convertOrderMessageDtoToString(OrderMessageDto orderMessageDTO) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(orderMessageDTO);
    }
}

