package com.example.android_ladob.service;

import com.example.android_ladob.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductsService {

    @GET("products")
    Call<List<Products>> getAllProducts();

    @GET("products/{id}")
    Call<Products> getProducts(@Path("id") Long id);

    @DELETE("products/{id}")
    Call<Products> delete(@Path("id") Long id);

    @POST("products")
    Call<Products> create(@Body Products products);

    @PUT("products/{id}")
    Call<Products> update(@Path("id") Long id, Products products);
}
