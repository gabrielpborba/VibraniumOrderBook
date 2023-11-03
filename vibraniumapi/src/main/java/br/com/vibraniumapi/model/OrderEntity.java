package br.com.vibraniumapi.model;


import br.com.vibraniumapi.dto.UserDto;
import jakarta.persistence.*;

import java.time.Instant;


@Entity(name="order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderType type;
    private double price;
    private int quantity;

    private Instant instant;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public OrderEntity() {
    }


    public OrderEntity(OrderType type, double price, int quantity, UserDto userDto) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.user =  userDto.toUserEntity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
