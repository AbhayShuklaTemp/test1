package com.example.biddingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class MaxBid {
    @Id
    @NotNull
    private String productId;
    @NotNull
    private String userId;
    @NotNull
    private Double amount;

    public MaxBid(String productId, String userId, Double amount) {
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
    }

    public MaxBid() {

    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
