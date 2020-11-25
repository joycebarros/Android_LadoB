package com.example.android_ladob.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.android_ladob.dao.ProductOrderDAO;
import com.example.android_ladob.dao.ProductOrderTempDAO;
import com.example.android_ladob.dao.ProductsDAO;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.ProductOrderTemp;
import com.example.android_ladob.model.Products;

@Database(entities = {Products.class, ProductOrder.class, ProductOrderTemp.class}, version=1)
@TypeConverters(Converters.class)
public abstract class RoomConfig extends RoomDatabase {

    private static RoomConfig instance = null;

    public abstract ProductsDAO productsDAO();
    public abstract ProductOrderDAO productOrderDAO();
    public abstract ProductOrderTempDAO productOrderTempDAO();

    public static RoomConfig getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, RoomConfig.class, "databaseName").allowMainThreadQueries().build();
        }
        return instance;
    }
}
