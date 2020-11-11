package com.example.android_ladob.service;

import com.example.android_ladob.model.Costumers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CostumersService {

    @GET("costumers")
    Call<List<Costumers>> getAllCostumers();

    @GET("costumers/{id}")
    Call<Costumers> getCostumers(@Path("id") Long id);

    @DELETE("costumers/{id}")
    Call<Costumers> delete(@Path("id") Long id);

    @POST("costumers")
    Call<Costumers> create(@Body Costumers costumers);

    @PUT("costumers/{id}")
    Call<Costumers> update(@Path("id") Long id, Costumers costumers);
}
