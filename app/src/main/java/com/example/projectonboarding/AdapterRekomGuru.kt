package com.example.projectonboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectonboarding.R.layout.rekomendasi_guru

class AdapterRekomGuru constructor(private val getFragment: BerandaFragment,
                                   private val berandarow: List<ItemRekomendasiGuru>):
    RecyclerView.Adapter<AdapterRekomGuru.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(rekomendasi_guru, parent, false)
        return MyViewHolder(view)
    }




    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = berandarow[position]
        holder.textNamaGuru.text = item.textNamaGuru
        holder.textMapel.text = item.textMapel
        holder.imageRekomGuru.setImageResource(item.drawableResId)
        holder.textSD.text = item.textSD
        holder.textSMP.text = item.textSMP
        holder.textTahun.text = item.textTahun
    }


    override fun getItemCount(): Int {
        return berandarow.size
    }
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textNamaGuru: TextView = itemView.findViewById(R.id.textNamaGuru)
        val textMapel: TextView = itemView.findViewById(R.id.textMapel)
        val imageRekomGuru: ImageView = itemView.findViewById(R.id.imageRekomGuru)
        val textSD: TextView = itemView.findViewById(R.id.textSD)
        val textSMP: TextView = itemView.findViewById(R.id.textSMP)
        val textTahun: TextView = itemView.findViewById(R.id.textTahun)

    }

    // Fungsi untuk mengatur RecyclerView menjadi horizontal
    fun setHorizontalLayout(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(getFragment.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
    }

}