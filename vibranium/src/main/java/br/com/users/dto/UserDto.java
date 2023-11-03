package br.com.users.dto;

import br.com.users.model.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDto {

    private Long id;

    @NotBlank @NotNull(message = "You need to provide a name")
    private String name;
    private double amount;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity toUserEntity() {
        return new UserEntity(this.id, this.name, this.amount, this.quantity);

    }

    public UserDto(Long id, String name, double amount, int quantity) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
    }
}
