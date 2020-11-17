package com.example.android_ladob.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.adapter.ProductsAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.repository.ResultEventProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView = findViewById(R.id.rv_products);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        getAllProducts(new ResultEventProducts() {
            @Override
            public void onResult(List<Products> products) {
                productsAdapter = new ProductsAdapter(ProductsActivity.this, products);
                recyclerView.setAdapter(productsAdapter);

            }

            @Override
            public void onFail(String message) {
                Toast.makeText(ProductsActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getAllProducts (final ResultEventProducts resultEventProducts){
            Call<List<Products>> call = new RetrofitConfig().getProductsService().getAllProducts();
            call.enqueue(new Callback<List<Products>>() {
                @Override
                public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                    List<Products> productsList = response.body();
                    resultEventProducts.onResult(productsList);
                }

                @Override
                public void onFailure(Call<List<Products>> call, Throwable t) {
                    resultEventProducts.onFail("Falha na requisição!");
                }
            });
        }
    }
