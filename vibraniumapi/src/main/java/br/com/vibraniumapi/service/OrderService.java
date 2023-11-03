package br.com.vibraniumapi.service;

import br.com.vibraniumapi.model.OrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
     void create(OrderEntity orderEntity) throws JsonProcessingException;

}