package com.example.android_ladob.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_ladob.model.ProductOrder;

import java.util.List;

@Dao
public interface ProductOrderDAO {

    @Query("SELECT * FROM ProductOrder")
    public List<ProductOrder> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<ProductOrder> productOrders);
}
