package com.example.android_ladob.repository;

import com.example.android_ladob.model.Products;

import java.util.List;

public interface ResultEventProducts {

    void onResult(List<Products> products);
    void onFail(String message);
}
