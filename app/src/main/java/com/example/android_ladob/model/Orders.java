package com.example.android_ladob.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {

    private Long id;
    private Date orderDate;
    private Time orderTime;
    private List<ProductOrder> productOrder;

    public Orders() {

    }

    public Orders(Date orderDate, Time orderTime) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public List<ProductOrder> getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(List<ProductOrder> productOrder) {
        this.productOrder = productOrder;
    }
}
