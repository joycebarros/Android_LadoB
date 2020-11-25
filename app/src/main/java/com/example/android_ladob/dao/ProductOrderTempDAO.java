package com.example.android_ladob.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.ProductOrderTemp;

import java.util.List;

@Dao
public interface ProductOrderTempDAO {

    @Query("SELECT * FROM ProductOrderTemp")
    public List<ProductOrderTemp> getAll();

    @Query("SELECT * FROM ProductOrderTemp")
    public List<ProductOrder> getAllProductOrder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAll(List<ProductOrderTemp> productOrderTemps);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertProductOrder(ProductOrderTemp productOrderTemp);

    @Query("DELETE FROM ProductOrderTemp")
    public void deleteAll();

    @Delete
    public void deleteProduct(ProductOrderTemp productOrderTemp);
}
