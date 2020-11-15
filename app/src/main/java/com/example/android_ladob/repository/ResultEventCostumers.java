package com.example.android_ladob.repository;

import com.example.android_ladob.model.Costumers;

import java.util.List;

public interface ResultEventCostumers {

    void onResult(List<Costumers> costumers);
    void onResult(Costumers costumers);
    void onFail(String message);
}
