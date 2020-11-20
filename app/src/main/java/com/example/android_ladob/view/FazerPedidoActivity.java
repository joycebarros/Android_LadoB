package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.adapter.ProductOrderAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.model.Orders;
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
    private ProductOrder productOrder;
    private TextView totalPedido;
    private TextView addMaisItens;
    private Intent intent;
    private int quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);

        recyclerView = findViewById(R.id.rv_productsPedido);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalPedido = findViewById(R.id.tv_totalPedido);
        addMaisItens = findViewById(R.id.txt_AddMais);
        Button btFazerPedido = findViewById(R.id.btFazerPedido);

        products = (Products) getIntent().getSerializableExtra(AddProductsActivity.ITEM_ID_EXTRA);
        quantidade = (int) getIntent().getSerializableExtra(AddProductsActivity.ITEM_QUANTIDADE_EXTRA);

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(FazerPedidoActivity.this, ProductsActivity.class);
                finish();
            }
        });

        btFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProductOrder(productOrder);
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

        addMaisItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(FazerPedidoActivity.this, AddProductsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getProductOrder(Long id, final ResultEventProductOrder resultEventProductOrder){
        Call<ProductOrder> call = new RetrofitConfig().getProductOrderService().getProductOrder(id);

        call.enqueue(new Callback<ProductOrder>() {
            @Override
            public void onResponse(Call<ProductOrder> call, Response<ProductOrder> response) {
                ProductOrder productOrder = response.body();
                productOrderList = new ArrayList<>();
                productOrderList.add(productOrder);
                resultEventProductOrder.onResult(productOrderList);

                Double total = productOrder.getProducts().getUnitPrice() * productOrder.getQuantity();
                totalPedido.setText(String.valueOf(total));

            }

            @Override
            public void onFailure(Call<ProductOrder> call, Throwable t) {
                resultEventProductOrder.onFail("Falha na requisição!");
            }
        });
    }

    public void createProductOrder(ProductOrder productOrder){
        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setQuantity(quantidade);
        Orders orders = new Orders();
        orders.setId((long) 1);
        productOrder1.setOrders(orders);
        productOrder1.setProducts(products);

        Call<ProductOrder> call = new RetrofitConfig().getProductOrderService().create(productOrder1);

        call.enqueue(new Callback<ProductOrder>() {
            @Override
            public void onResponse(Call<ProductOrder> call, Response<ProductOrder> response) {
                if(response.isSuccessful()){
                    ProductOrder productOrder2 = response.body();
                    Toast.makeText(FazerPedidoActivity.this, "Sucesso ao criar o ProductOrder", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(FazerPedidoActivity.this, "Erro no sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProductOrder> call, Throwable t) {
                Toast.makeText(FazerPedidoActivity.this, "Falha ao criar o ProductOrder!", Toast.LENGTH_LONG).show();
            }
        });

    }
}