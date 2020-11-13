package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android_ladob.R;

public class CostumersActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costumers);

        linearLayout = findViewById(R.id.llProducts);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CostumersActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });
        linearLayout = findViewById(R.id.llOrder);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CostumersActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        linearLayout = findViewById(R.id.llPagamento);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CostumersActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });
        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CostumersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}