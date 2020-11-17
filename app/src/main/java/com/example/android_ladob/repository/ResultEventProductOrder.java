package com.example.android_ladob.repository;

import com.example.android_ladob.model.ProductOrder;

import java.util.List;

public interface ResultEventProductOrder {

    void onResult(List<ProductOrder> productOrders);
    void onFail(String message);
}
