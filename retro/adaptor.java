package com.example.retro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Callback;

public class adaptor extends RecyclerView.Adapter<adaptor.ViewHolder> {
    private ArrayList<Model> models;
    private Context context;
    public adaptor(Context context, ArrayList<Model> models) {
        this.models = models;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.isim.setText(models.get(i).getIsim());
        viewHolder.kisi.setText(models.get(i).getKisi());
        viewHolder.rank.setText(models.get(i).getRank());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView isim,kisi,rank;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            isim=itemView.findViewById(R.id.isim);
            kisi=itemView.findViewById(R.id.kim);
            rank=itemView.findViewById(R.id.rank);
        }
    }
}
