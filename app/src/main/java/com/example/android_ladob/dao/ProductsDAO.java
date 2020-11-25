package com.example.android_ladob.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_ladob.model.Products;

import java.util.List;
@Dao
public interface ProductsDAO {

    @Query("SELECT * FROM Products")
    public List<Products> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<Products> products);
}
