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
import com.example.android_ladob.adapter.FazerPedidoAdapter;
import com.example.android_ladob.adapter.ProductOrderAdapter;
import com.example.android_ladob.adapter.ProductsAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.config.RoomConfig;
import com.example.android_ladob.model.Orders;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.ProductOrderTemp;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.repository.ResultEventProductOrder;
import com.example.android_ladob.repository.ResultEventProducts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FazerPedidoActivity extends AppCompatActivity {

    private Products products;
    private RecyclerView recyclerView;
    private FazerPedidoAdapter fazerPedidoAdapter;
    private ProductOrder productOrder;
    private TextView totalPedido;
    private TextView addMaisItens;
    private Intent intent;
    private int quantidade;
    private TextView tvQuantidade;
    private TextView tvValorTotalItem;
    private List<ProductOrderTemp> productOrderTemps;
    private ProductOrderTemp productOrderTemp;
    private RoomConfig dbInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_pedido);

        dbInstance = RoomConfig.getInstance(this);

        recyclerView = findViewById(R.id.rv_productsPedido);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalPedido = findViewById(R.id.tv_totalPedido);
        addMaisItens = findViewById(R.id.txt_AddMais);
        Button btFazerPedido = findViewById(R.id.btFazerPedido);

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(FazerPedidoActivity.this, ProductsActivity.class);
                //fechar todas as telas anteriores
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        productOrderTemps = dbInstance.productOrderTempDAO().getAll();
        fazerPedidoAdapter = new FazerPedidoAdapter(FazerPedidoActivity.this, productOrderTemps);
        recyclerView.setAdapter(fazerPedidoAdapter);
        getTotal(productOrderTemps);

        btFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FazerPedidoActivity.this, CostumersActivity.class);
                createProductOrder(productOrderTemps);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        addMaisItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(FazerPedidoActivity.this, ProductsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void getTotal(List<ProductOrderTemp> productOrderTemps){
        Double unitPrice = 0.0;
        int quantity = 0;
        Double total = 0.0;
        for(ProductOrderTemp productOrderTemp1: productOrderTemps){
            unitPrice = productOrderTemp1.getProducts().getUnitPrice();
            quantity = productOrderTemp1.getQuantity();
            total = total + (unitPrice * quantity);
        }
        totalPedido.setText(String.format("R$ %.2f", total));
    }
 
    public void createProductOrder(List<ProductOrderTemp> productOrderTemps){

       List<ProductOrder> productOrderList = dbInstance.productOrderTempDAO().getAllProductOrder();
       for(ProductOrder productOrder1: productOrderList){
           productOrder1.getQuantity();
           Orders orders = new Orders();
           orders.setId((long) 1);
           productOrder1.setOrders(orders);
           productOrder1.getProducts();

           Call<ProductOrder> call = new RetrofitConfig().getProductOrderService().create(productOrder1);

           call.enqueue(new Callback<ProductOrder>() {
               @Override
               public void onResponse(Call<ProductOrder> call, Response<ProductOrder> response) {
                   if(response.isSuccessful()){
                       ProductOrder productOrder2 = response.body();
                       Toast.makeText(FazerPedidoActivity.this, "Pedido realizado com Sucesso!", Toast.LENGTH_LONG).show();
                   }else{
                       Toast.makeText(FazerPedidoActivity.this, "Erro no sucesso!", Toast.LENGTH_LONG).show();
                   }
               }

               @Override
               public void onFailure(Call<ProductOrder> call, Throwable t) {
                   Toast.makeText(FazerPedidoActivity.this, "Falha ao criar o Pedido!", Toast.LENGTH_LONG).show();
               }
           });

       }
    }
}