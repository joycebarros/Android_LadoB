package com.example.android_ladob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ladob.R;
import com.example.android_ladob.model.Costumers;

import java.util.List;

public class CostumersAdapter extends RecyclerView.Adapter<CostumersAdapter.CostumersHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private List<Costumers> costumers;

    public CostumersAdapter(Context context, List<Costumers> costumers) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.costumers = costumers;
    }

    @NonNull
    @Override
    public CostumersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_costumers, parent, false);
        return new CostumersHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CostumersHolder holder, int position) {
        //  Picasso.get().load("https://ead.catolica.edu.br/hubfs/Blog/0-por-que-ser-professor-ainda-e-muito-importante.jpg").into(holder.imageView);
        holder.textname.setText(costumers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return costumers.size();
    }


    public class CostumersHolder extends RecyclerView.ViewHolder{

        TextView textname;

        public CostumersHolder(@NonNull View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.txt_name);

        }
    }
}
