package br.com.users.service;

import br.com.users.model.OrderEntity;
import br.com.users.model.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OrderService {
     void create(OrderEntity orderEntity) throws JsonProcessingException;

}