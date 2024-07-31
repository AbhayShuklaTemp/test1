package com.example.biddingservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@Entity
public class Product {
    @NotNull
    @Id
    private String productId;
    @NotNull
    private String vendorId;
    @NotNull
    private String productName;
    private String productDescription;
    @NotNull
    private Double basePrice;
    @NotNull
    private String productCategory;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime slotStart;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime slotEnd;

    // Constructors, getters, setters
    public Product() {}

    public Product(String productId, String vendorId, String productName, String productDescription, Double basePrice, String productCategory, LocalDateTime slotStart, LocalDateTime slotEnd) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.basePrice = basePrice;
        this.productCategory = productCategory;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
    }

    public Product(String productId, String vendorId, Double basePrice, String productCategory, LocalDateTime slotStart, LocalDateTime slotEnd) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.basePrice = basePrice;
        this.productCategory = productCategory;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public LocalDateTime getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(LocalDateTime slotStart) {
        this.slotStart = slotStart;
    }

    public LocalDateTime getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(LocalDateTime slotEnd) {
        this.slotEnd = slotEnd;
    }
}
