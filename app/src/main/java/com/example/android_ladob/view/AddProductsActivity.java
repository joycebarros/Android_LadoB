package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.adapter.ProductsAdapter;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.config.RoomConfig;
import com.example.android_ladob.model.Orders;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.ProductOrderTemp;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.repository.ResultEventProducts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductsActivity extends AppCompatActivity {

    private Products products;
    private Button btAdicionar;
    private Button btMenos;
    private Button btMais;
    private TextView tvQuantidade;
    private int totalQuantidade = 1;
    private ImageView btCancelar;
    private TextView valor;
    private ProductOrder productOrder;
    private List<ProductOrder> productOrderList;
    private RoomConfig dbInstance;
    private Orders orders;
    private ProductOrderTemp productOrderTemp;
    private List<ProductOrderTemp> productOrderTemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item_selecionado);

        dbInstance = RoomConfig.getInstance(this);
        //dbInstance.productOrderTempDAO().deleteAll();

        products = (Products) getIntent().getSerializableExtra(ProductsAdapter.ITEM_ID_EXTRA);

        btAdicionar = findViewById(R.id.btAdd);
        btMenos = findViewById(R.id.btMenos);
        btMais = findViewById(R.id.btMais);
        tvQuantidade =findViewById(R.id.tv_quantidade);
        btCancelar = findViewById(R.id.imag_cancel);

        TextView name = findViewById(R.id.tv_produto_name);
        TextView descricao = findViewById(R.id.tv_produto_descricao);
        valor = findViewById(R.id.tv_valor);
        name.setText(products.getName());
        descricao.setText(products.getDescription());
        valor.setText(String.format("R$ %.2f", products.getUnitPrice()));

        setOnClickOnButtons();

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productOrderTemp = createProductOrderTemp(totalQuantidade, orders, products);
                dbInstance.productOrderTempDAO().insertProductOrder(productOrderTemp);

                Intent intent = new Intent(AddProductsActivity.this, FazerPedidoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setOnClickOnButtons(){
        btMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalQuantidade > 0){
                    totalQuantidade = totalQuantidade - 1;
                } else {
                    Toast.makeText(AddProductsActivity.this, "A quantidade precisa ser maior do que zero", Toast.LENGTH_LONG).show();
                }
                tvQuantidade.setText(String.valueOf(totalQuantidade));
                valor.setText(String.format("R$ %.2f", products.getUnitPrice() * totalQuantidade));
            }
        });

        btMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalQuantidade = totalQuantidade + 1;
                tvQuantidade.setText(String.valueOf(totalQuantidade));
                valor.setText(String.format("R$ %.2f", products.getUnitPrice() * totalQuantidade));
            }
        });

    }

    public ProductOrderTemp createProductOrderTemp (Integer quantity, Orders orders, Products products){
        ProductOrderTemp productOrderTemp1 = new ProductOrderTemp();
        productOrderTemp1.setQuantity(quantity);
        orders = new Orders();
        orders.setId((long) 1);
        productOrderTemp1.setOrders(orders);
        productOrderTemp1.setProducts(products);
        return productOrderTemp1;
    }
}