package com.example.projectonboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<dbBerita> dbBeritaArrayList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public MyAdapter(Context context, ArrayList<dbBerita> dbBeritaArrayList,OnItemClickListener listener) {
        this.context = context;
        this.dbBeritaArrayList = dbBeritaArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_berita,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        dbBerita berita = dbBeritaArrayList.get(position);
        holder.tanggal.setText(berita.tanggal);
        holder.judul.setText(berita.judul);
        holder.author.setText(berita.author);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int clickedPosition = holder.getAdapterPosition();
                    if (clickedPosition != RecyclerView.NO_POSITION) {
                        listener.onItemClick(clickedPosition);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dbBeritaArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tanggal,judul,author;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tvTanggal);
            judul = itemView.findViewById(R.id.tvJudul);
            author = itemView.findViewById(R.id.tvAuthor);
        }
    }
}
