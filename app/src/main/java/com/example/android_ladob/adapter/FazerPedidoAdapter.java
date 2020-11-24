package com.example.android_ladob.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ladob.R;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.view.ProductsActivity;

import java.util.List;

public class FazerPedidoAdapter extends RecyclerView.Adapter<FazerPedidoAdapter.FazerPedidoHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<Products> products;

    public FazerPedidoAdapter(Context context, List<Products> products) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.products = products;
    }

    @NonNull
    @Override
    public FazerPedidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_fazer_pedido, parent, false);
        return new FazerPedidoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FazerPedidoHolder holder, int position) {
        holder.tvName.setText(products.get(position).getName());
        holder.tvDescricao.setText(products.get(position).getDescription());
        holder.tvValor.setText(String.valueOf(products.get(position).getUnitPrice()));
//        holder.tvQuantidade.setText(String.valueOf(products.get(position).getProductOrder().get(position).getQuantity()));
//        holder.tvValorTotal.setText(String.valueOf(products.get(position).getUnitPrice()
//                * products.get(position).getProductOrder().get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class FazerPedidoHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDescricao;
        TextView tvValor;
//        TextView tvQuantidade;
//        TextView tvValorTotal;
        ImageView excluir;

        public FazerPedidoHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_produto_name);
            tvDescricao = itemView.findViewById(R.id.tv_produto_descricao);
            tvValor = itemView.findViewById(R.id.tv_valor);
        //    tvQuantidade = itemView.findViewById(R.id.tv_quantidade2);
        //    tvValorTotal = itemView.findViewById(R.id.tv_total);
            excluir = itemView.findViewById(R.id.imag_excluir);

            excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            });

        }
    }
}
