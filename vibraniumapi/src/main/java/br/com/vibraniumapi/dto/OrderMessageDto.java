package br.com.vibraniumapi.dto;

import br.com.vibraniumapi.model.OrderEntity;

public class OrderMessageDto {
    private OrderEntity order;

    public OrderMessageDto(OrderEntity order ) {
        this.order = order;
    }
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}