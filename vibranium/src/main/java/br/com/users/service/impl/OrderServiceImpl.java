package br.com.users.service.impl;

import br.com.users.component.OrderQueueProducer;
import br.com.users.exceptions.InsufficientBalanceException;
import br.com.users.exceptions.InsufficientVibraniumException;
import br.com.users.dto.OrderMessageDto;
import br.com.users.model.OrderEntity;
import br.com.users.model.OrderType;
import br.com.users.model.UserEntity;
import br.com.users.service.OrderService;
import br.com.users.service.UserService;
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
            Optional<UserEntity> user = userService.findById(orderEntity.getUser().getId());

            if(user.isPresent()){
                if(OrderType.BUY.equals(orderEntity.getType())){
                    if (user.get().getAmount() <=  orderEntity.getQuantity() * orderEntity.getPrice()){
                        throw new InsufficientBalanceException(user.get().getAmount(), orderValue);
                    }

                }else if(OrderType.SELL.equals(orderEntity.getType())){
                    if(user.get().getQuantity() < orderEntity.getQuantity()){
                        throw new InsufficientVibraniumException(user.get().getQuantity(), orderEntity.getQuantity());
                    }
                }
            }

            orderEntity.setInstant(Instant.now());
            orderEntity.setAvailable(true);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            OrderMessageDto orderMessageDTO = new OrderMessageDto(orderEntity);

            String message = objectMapper.writeValueAsString(orderMessageDTO);
            orderQueueProducer.sendMessage(message);

    }
}

