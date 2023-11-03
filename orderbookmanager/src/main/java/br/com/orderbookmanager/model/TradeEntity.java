package br.com.orderbookmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="trade_entity")
public class TradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long idBuyer ;
    private long idSeller;
    private double price;
    private int quantity;


    public TradeEntity() {
    }

    public TradeEntity(long idBuyer, long idSeller, double price, int quantity) {
        this.idBuyer = idBuyer;
        this.idSeller = idSeller;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(long idBuyer) {
        this.idBuyer = idBuyer;
    }

    public long getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(long idSeller) {
        this.idSeller = idSeller;
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
}
