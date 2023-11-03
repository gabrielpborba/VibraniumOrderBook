package br.com.orderbookmanager.service;


import br.com.orderbookmanager.model.OrderEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.criteria.Order;

public interface OrderService {
     void matchOrders(OrderEntity order) throws JsonProcessingException;

     void save(OrderEntity orderEntity);

}