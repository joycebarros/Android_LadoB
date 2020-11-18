package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.adapter.ProductOrderAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.repository.ResultEventProductOrder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FazerPedidoActivity extends AppCompatActivity {

    private Products products;
    private RecyclerView recyclerView;
    private ProductOrderAdapter productOrderAdapter;
    private List<ProductOrder> productOrderList;
    private TextView totalPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);

        recyclerView = findViewById(R.id.rv_productsPedido);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalPedido = findViewById(R.id.tv_totalPedido);

        products = (Products) getIntent().getSerializableExtra(AddProductsActivity.ITEM_ID_EXTRA);

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FazerPedidoActivity.this, AddProductsActivity.class);
                finish();
            }
        });

        getProductOrder(products.getId(), new ResultEventProductOrder() {
            @Override
            public void onResult(List<ProductOrder> productOrders) {
                productOrderAdapter = new ProductOrderAdapter(FazerPedidoActivity.this, productOrders);
                recyclerView.setAdapter(productOrderAdapter);
            }

            @Override
            public void onFail(String message) {
                Toast.makeText(FazerPedidoActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void getProductOrder(Long id, final ResultEventProductOrder resultEventProductOrder){
        Call<ProductOrder> call = new RetrofitConfig().getProductOrderService().getProductOrder(id);

        call.enqueue(new Callback<ProductOrder>() {
            @Override
            public void onResponse(Call<ProductOrder> call, Response<ProductOrder> response) {
                ProductOrder productOrder = response.body();
                List<ProductOrder> productOrderList = new ArrayList<>();
                productOrderList.add(productOrder);
                resultEventProductOrder.onResult(productOrderList);
                Double total = 0.0;
                for (ProductOrder productOrder1 : productOrderList) {
                    Double valor = productOrder1.getProducts().getUnitPrice() * productOrder1.getQuantity();
                    total = total + valor;
                }
                totalPedido.setText(String.valueOf(total));

            }

            @Override
            public void onFailure(Call<ProductOrder> call, Throwable t) {
                resultEventProductOrder.onFail("Falha na requisição!");
            }
        });
    }
}