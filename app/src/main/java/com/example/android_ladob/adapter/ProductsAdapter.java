package com.example.android_ladob.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ladob.R;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.view.AddProductsActivity;
import com.example.android_ladob.view.ProductsActivity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private List<Products> products;
    public final static String ITEM_ID_EXTRA = "ProdutoID";

    public ProductsAdapter(Context context, List<Products> products) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_products, parent, false);
        return new ProductsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder holder, int position) {
        holder.textname.setText(products.get(position).getName());
        holder.textdescription.setText(products.get(position).getDescription());
        holder.textvalor.setText(String.format("R$ %.2f", products.get(position).getUnitPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textname;
        TextView textdescription;
        TextView textvalor;
        ConstraintLayout parentLayout;

        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_queijudo);
            textname = itemView.findViewById(R.id.tv_produto_name);
            textdescription = itemView.findViewById(R.id.tv_produto_descricao);
            textvalor = itemView.findViewById(R.id.tv_valor);
            parentLayout = itemView.findViewById(R.id.parent_container);

            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddProductsActivity.class);
                    Products product =  products.get(getAdapterPosition());
                    intent.putExtra(ITEM_ID_EXTRA, product);
                    context.startActivity(intent);
                }
            });
        }
    }
}
