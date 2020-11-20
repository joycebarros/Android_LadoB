package com.example.android_ladob.service;

import com.example.android_ladob.model.ProductOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductOrderService {

    @GET("productorder")
    Call<List<ProductOrder>> getAllProductOrder();

    @GET("productorder/{id}")
    Call<ProductOrder> getProductOrder(@Path("id") Long id);

    @POST("productorder")
    Call<ProductOrder> create(@Body ProductOrder productOrder);

}
