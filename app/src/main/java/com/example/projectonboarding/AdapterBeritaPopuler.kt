package com.example.projectonboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterBeritaPopuler constructor(private val getFragment: BerandaFragment,
                                       private val berandarow: List<ItemBeritaPopuler>):
    RecyclerView.Adapter<AdapterBeritaPopuler.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.populer_berita, parent, false)
        return MyViewHolder(view)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textTanggal.text = berandarow[position].textTanggal
        holder.textJudulBerita.text = berandarow[position].textJudulBerita
        holder.imageBerita.setImageResource(berandarow[position].drawableResId)
        holder.textNamaPenulis.text = berandarow[position].textNamaPenulis
        holder.textWaktu.text = berandarow[position].textWaktu

    }

    override fun getItemCount(): Int {
        return berandarow.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textTanggal: TextView = itemView.findViewById(R.id.textTanggal)
        val textJudulBerita: TextView = itemView.findViewById(R.id.textJudulBerita)
        val imageBerita: ImageView = itemView.findViewById(R.id.imageBerita)
        val textNamaPenulis: TextView = itemView.findViewById(R.id.textNamaPenulis)
        val textWaktu: TextView = itemView.findViewById(R.id.textWaktu)

    }

}