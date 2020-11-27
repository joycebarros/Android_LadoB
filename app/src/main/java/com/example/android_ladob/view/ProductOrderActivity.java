package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.adapter.ProductOrderAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.repository.ResultEventProductOrder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductOrderAdapter productOrderAdapter;
    private ProductOrder productOrder;
    private List<ProductOrder> productOrderList;
    private TextView tvTotalPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_order);

        recyclerView = findViewById(R.id.rv_productsPedido);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tvTotalPedido = findViewById(R.id.tv_totalPedido);

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductOrderActivity.this, CostumersActivity.class);
                finish();
            }
        });

        Button btFecharConta = findViewById(R.id.btFecharConta);

        btFecharConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductOrderActivity.this, "Sua conta está sendo fechada!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProductOrderActivity.this, CostumersActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        getAllProductOrder(new ResultEventProductOrder() {
            @Override
            public void onResult(List<ProductOrder> productOrders) {
                productOrderAdapter = new ProductOrderAdapter(ProductOrderActivity.this, productOrders);
                recyclerView.setAdapter(productOrderAdapter);
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(ProductOrderActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getAllProductOrder (final ResultEventProductOrder resultEventProductOrder){
        Call<List<ProductOrder>> call = new RetrofitConfig().getProductOrderService().getAllProductOrder();

        call.enqueue(new Callback<List<ProductOrder>>() {
            @Override
            public void onResponse(Call<List<ProductOrder>> call, Response<List<ProductOrder>> response) {
                productOrderList = response.body();
                resultEventProductOrder.onResult(productOrderList);
                Double total = 0.00;
                for (ProductOrder productOrder : productOrderList) {
                    Double valor = productOrder.getProducts().getUnitPrice() * productOrder.getQuantity();
                    total = total + valor;
                }
                tvTotalPedido.setText(String.format("R$ %.2f", total));
            }

            @Override
            public void onFailure(Call<List<ProductOrder>> call, Throwable t) {
                resultEventProductOrder.onFail("Falha na requisição!");
            }
        });
    }
}