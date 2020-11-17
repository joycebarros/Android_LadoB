package com.example.android_ladob.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ladob.R;
import com.example.android_ladob.model.ProductOrder;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.view.AddProductsActivity;
import com.example.android_ladob.view.PagamentoActivity;

import java.util.List;

public class ProductOrderAdapter extends RecyclerView.Adapter<ProductOrderAdapter.ProductOrdersHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<ProductOrder> productOrders;

    public ProductOrderAdapter(Context context, List<ProductOrder> productOrders) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.productOrders = productOrders;
    }

    @NonNull
    @Override
    public ProductOrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_productorder, parent, false);
        return new ProductOrdersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductOrdersHolder holder, int position) {
        holder.tvName.setText(productOrders.get(position).getProducts().getName());
        holder.tvDescricao.setText(productOrders.get(position).getProducts().getDescription());
        holder.tvValor.setText(String.valueOf(productOrders.get(position).getProducts().getUnitPrice()));
    }

    @Override
    public int getItemCount() {
        return productOrders.size();
    }


    public class ProductOrdersHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDescricao;
        TextView tvValor;

        public ProductOrdersHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_produto_name);
            tvDescricao = itemView.findViewById(R.id.tv_produto_descricao);
            tvValor = itemView.findViewById(R.id.tv_valor);

        }
    }
}