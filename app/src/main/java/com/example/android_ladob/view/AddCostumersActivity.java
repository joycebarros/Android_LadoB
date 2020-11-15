package com.example.android_ladob.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_ladob.R;
import com.example.android_ladob.config.RetrofitConfig;
import com.example.android_ladob.model.Costumers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCostumersActivity extends AppCompatActivity {
    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private EditText campoSenha;
    private Costumers costumers;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_costumers);

        Button bSalvar = findViewById(R.id.btnSalvar);
        campoNome = findViewById(R.id.edtNome);
        campoEmail = findViewById(R.id.edtEmail);
        campoTelefone = findViewById(R.id.edtTelefone);
        campoSenha = findViewById(R.id.edtSenha);

        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCostumer(costumers);
                intent = new Intent(AddCostumersActivity.this, MainActivity.class);
                finish();
            }
        });

        ImageView imageView = findViewById(R.id.imag_voltar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddCostumersActivity.this, MainActivity.class);
                finish();
            }
        });
    }

    private void createCostumer(Costumers costumers) {

        costumers = new Costumers();
        String name = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telephone = campoTelefone.getText().toString();
        String password = campoSenha.getText().toString();
        costumers.setName(name);
        costumers.setEmail(email);
        costumers.setTelephone(telephone);
        costumers.setPassword(password);

        Call<Costumers> call = new RetrofitConfig().getCostumersService().create(costumers);
        call.enqueue(new Callback<Costumers>() {
            @Override
            public void onResponse(Call<Costumers> call, Response<Costumers> response) {
                if(response.isSuccessful()){
                    Costumers costumers1 = response.body();
                    Toast.makeText(AddCostumersActivity.this, "Sucesso ao criar o costumer!", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(AddCostumersActivity.this, "Erro no sucesso", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Costumers> call, Throwable t) {
                Toast.makeText(AddCostumersActivity.this, "Falha ao criar o costumer!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}