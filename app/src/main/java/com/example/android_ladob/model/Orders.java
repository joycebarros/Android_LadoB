package com.example.android_ladob.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Orders implements Serializable {

    private Long id;
    private Date orderDate;
    private Time orderTime;

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

}
