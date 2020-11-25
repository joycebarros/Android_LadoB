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
import com.example.android_ladob.config.RoomConfig;
import com.example.android_ladob.model.ProductOrderTemp;
import com.example.android_ladob.model.Products;
import com.example.android_ladob.view.FazerPedidoActivity;
import com.example.android_ladob.view.ProductsActivity;

import java.util.List;

public class FazerPedidoAdapter extends RecyclerView.Adapter<FazerPedidoAdapter.FazerPedidoHolder> {

    private Context context;
    private LayoutInflater mInflater;
    private List<ProductOrderTemp> productOrderTemps;
    private RoomConfig dbInstance;

    public FazerPedidoAdapter(Context context, List<ProductOrderTemp> productOrderTemps) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.productOrderTemps = productOrderTemps;
    }

    @NonNull
    @Override
    public FazerPedidoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_fazer_pedido, parent, false);
        dbInstance = RoomConfig.getInstance(context);
        return new FazerPedidoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FazerPedidoHolder holder, int position) {
        holder.tvName.setText(productOrderTemps.get(position).getProducts().getName());
        holder.tvDescricao.setText(productOrderTemps.get(position).getProducts().getDescription());
        holder.tvValor.setText(String.valueOf(productOrderTemps.get(position).getProducts().getUnitPrice()));
        holder.tvQuantidade.setText(String.valueOf(productOrderTemps.get(position).getQuantity()));
        holder.tvValorTotal.setText(String.valueOf(productOrderTemps.get(position).getProducts().getUnitPrice()
                * productOrderTemps.get(position).getQuantity()));

    }

    @Override
    public int getItemCount() {
        return productOrderTemps.size();
    }

    public class FazerPedidoHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDescricao;
        TextView tvValor;
        TextView tvQuantidade;
        TextView tvValorTotal;
        ImageView excluir;

        public FazerPedidoHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_produto_name);
            tvDescricao = itemView.findViewById(R.id.tv_produto_descricao);
            tvValor = itemView.findViewById(R.id.tv_valor);
            tvQuantidade = itemView.findViewById(R.id.tv_quantidade2);
            tvValorTotal = itemView.findViewById(R.id.tv_total);
            excluir = itemView.findViewById(R.id.imag_excluir);

            excluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FazerPedidoActivity.class);
                    ProductOrderTemp productOrderTemp = productOrderTemps.get(getAdapterPosition());
                    dbInstance.productOrderTempDAO().deleteProduct(productOrderTemp);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            });
        }
    }
}
