package com.example.projectonboarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class guruAdapter extends RecyclerView.Adapter<guruAdapter.GuruViewHolder> {
    Context context;
    ArrayList<dbGuru> dbGuruArrayList;
    private OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public guruAdapter(Context context, ArrayList<dbGuru> dbGuruArrayList, OnItemClickListener listener) {
        this.context = context;
        this.dbGuruArrayList = dbGuruArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public guruAdapter.GuruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_guru,parent,false);

        return new GuruViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull guruAdapter.GuruViewHolder holder, int position) {
        dbGuru guru = dbGuruArrayList.get(position);
        holder.guru.setText(guru.getnama());
        holder.matpel.setText(guru.getMatpel());
        holder.rating.setText(guru.getRating());
        holder.jenjang1.setText(guru.getJenjang1());
        holder.jenjang2.setText(guru.getJenjang2());
        String url = guru.getFoto();
        Picasso.get().load(url).into(holder.gambar);
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
        return dbGuruArrayList.size();
    }
    public static class GuruViewHolder extends RecyclerView.ViewHolder{
        TextView guru,matpel,jenjang1,jenjang2;
        ImageView gambar;
        Button rating;
        public GuruViewHolder(@NonNull View itemView) {
            super(itemView);
            guru = itemView.findViewById(R.id.tvGuru);
            matpel = itemView.findViewById(R.id.tvMatpel);
            jenjang1 = itemView.findViewById(R.id.tvJenjang);
            jenjang2 = itemView.findViewById(R.id.tvJenjang2);
            gambar = itemView.findViewById(R.id.gambarGuru);
            rating = itemView.findViewById(R.id.btnRating);
        }
    }


}
