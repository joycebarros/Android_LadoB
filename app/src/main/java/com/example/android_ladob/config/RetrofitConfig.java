package com.example.android_ladob.config;

import com.example.android_ladob.service.CostumersService;
import com.example.android_ladob.service.ProductsService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://ladob.herokuapp.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public ProductsService getProductsService(){
        return retrofit.create(ProductsService.class);
    }

    public CostumersService getCostumersService(){
        return retrofit.create(CostumersService.class);
    }
}
