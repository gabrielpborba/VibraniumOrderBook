package br.com.vibraniumapi.dto;

import br.com.vibraniumapi.model.OrderEntity;
import br.com.vibraniumapi.model.OrderType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public class OrderDto {


    @NotNull(message = "The Order needs to be of type BUY or SELL")
    @Enumerated(EnumType.STRING)
    private OrderType type;
    @NotNull(message = "You must specify the Vibranium value you want to trade")
    private double price;
    @NotNull(message = "You must specify the quantity of Vibranium you want to trade")
    private int quantity;

    private UserDto user;


    public OrderEntity toOrderEntity() {
        return new OrderEntity(this.type, this.price, this.quantity, this.user = user);
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

}
