package com.example.biddingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Bid {
    @Id
    private String id;
    @NotNull
    private String productId;
    @NotNull
    private String userId;
    @NotNull
    private Double amount;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime bidTime;

    public static final String colon = ":";

    public Bid(String productId, String userId, Double amount, LocalDateTime bidTime) {
        this.id = productId+colon+userId;
        this.productId = productId;
        this.userId = userId;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    public Bid() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }
}
