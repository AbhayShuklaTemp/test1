package com.example.biddingservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductUser {
    @Id
    @NotNull
    private String userId;
    @NotNull
    private String userName;
    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    // category - Buyer/Vendor
    private UserCategory userCategory;

    // getter and setter is set
    public ProductUser(){

    }

    public ProductUser(@NotNull String userId, @NotNull String userName, @NotNull String password, @NotNull String email, @NotNull UserCategory userCategory) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userCategory = userCategory;
    }

    public ProductUser(@NotNull String userId, @NotNull String userName, @NotNull String email, @NotNull UserCategory userCategory) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userCategory = userCategory;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull String userId) {
        this.userId = userId;
    }

    @NotNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    @NotNull
    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(@NotNull UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
