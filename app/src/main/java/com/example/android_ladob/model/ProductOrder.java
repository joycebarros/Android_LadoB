package com.example.android_ladob.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class ProductOrder implements Serializable {
    @PrimaryKey
    private Long id;
    private Integer quantity;
    private Orders orders;
    private Products products;

    public ProductOrder(){

    }
    public ProductOrder(Integer quantity, Orders orders, Products products) {
        this.quantity = quantity;
        this.orders = orders;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
