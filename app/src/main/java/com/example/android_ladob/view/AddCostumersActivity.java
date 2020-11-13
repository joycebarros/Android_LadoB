package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.android_ladob.R;

public class AddCostumersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_costumers);

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCostumersActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}