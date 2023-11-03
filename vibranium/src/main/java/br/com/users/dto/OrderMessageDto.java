package br.com.users.dto;

import br.com.users.model.OrderEntity;

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