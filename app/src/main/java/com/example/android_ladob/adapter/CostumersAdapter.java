package com.example.android_ladob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_ladob.model.Costumers;

public class CostumersAdapter extends RecyclerView.Adapter<CostumersAdapter.CostumersHolder> {

    private Context context;
    private final LayoutInflater mInflater;
    private Costumers costumers;

    public CostumersAdapter(Context context, Costumers costumers) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.costumers = costumers;
    }

    @NonNull
    @Override
    public CostumersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CostumersHolder holder, int position) {
        //  Picasso.get().load("https://ead.catolica.edu.br/hubfs/Blog/0-por-que-ser-professor-ainda-e-muito-importante.jpg").into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class CostumersHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public CostumersHolder(@NonNull View itemView) {
            super(itemView);
//            imageView = itemView.findViewById(R.id.image_curso);
//            textView = itemView.findViewById(R.id.tv_curso_nome);

        }
    }
}
