package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {
     AdapterRekomGuru adapterRekomGuru;
    private ImageButton buttonNotification;
    RecyclerView rowRecyclerView;
    RecyclerView rowRecyclerViewBerita;
     List<ItemRekomendasiGuru> adapterRekomGuruList = new ArrayList<>();
    List<ItemBeritaPopuler> adapterBeritaPopulerList = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_beranda, container, false);
        addRekomGuru();
        rowRecyclerView = view.findViewById(R.id.recycle_rekom_guru);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rowRecyclerView.setLayoutManager(layoutManager);
        adapterRekomGuru = new AdapterRekomGuru(this, adapterRekomGuruList);
        rowRecyclerView.setAdapter(adapterRekomGuru);
        rowRecyclerView.setNestedScrollingEnabled(true);


        addBeritaPopular();
        rowRecyclerViewBerita = view.findViewById(R.id.recycle_populer_berita);
        LinearLayoutManager layoutManagerBerita = new LinearLayoutManager(requireContext());
        layoutManagerBerita.setOrientation(LinearLayoutManager.VERTICAL);
        AdapterBeritaPopuler adapterRekomGuruBerita = new AdapterBeritaPopuler(new BerandaFragment(), adapterBeritaPopulerList);
        rowRecyclerViewBerita.setLayoutManager(layoutManagerBerita);
        rowRecyclerViewBerita.setAdapter(adapterRekomGuruBerita);
        rowRecyclerViewBerita.setNestedScrollingEnabled(true);



        buttonNotification = view.findViewById(R.id.ButtonNotification);

        // Menambahkan onClickListener ke ImageButton
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode untuk pindah ke halaman notifikasi
                pindahKeHalamanNotifikasi();
            }
        });

        return view;
    }
    public void addRekomGuru(){
        ItemRekomendasiGuru irg = new ItemRekomendasiGuru(R.drawable.imguru1, "marji", "matkom", "SD","SMP", "2020");
        ItemRekomendasiGuru irg1 = new ItemRekomendasiGuru(R.drawable.imguru1, "eko", "aljabar imajiner", "SMA","Kuliah", "2020");
        ItemRekomendasiGuru irg2 = new ItemRekomendasiGuru(R.drawable.imguru1, "eko", "aljabar imajiner", "SMA","Kuliah", "2020");
        ItemRekomendasiGuru irg3 = new ItemRekomendasiGuru(R.drawable.imguru1, "eko", "aljabar imajiner", "SMA","Kuliah", "2020");
        adapterRekomGuruList.add(irg);
        adapterRekomGuruList.add(irg1);
        adapterRekomGuruList.add(irg2);
        adapterRekomGuruList.add(irg3);
    }

    public void addBeritaPopular(){
        ItemBeritaPopuler ibp = new ItemBeritaPopuler(R.drawable.gambarberita, "29 februari 20024", "seorang anak berhasil bangun tidur", "Albert einstein", "10 menit");
        ItemBeritaPopuler ibp1 = new ItemBeritaPopuler(R.drawable.gambarberita, "29 februari 20024", "seorang anak berhasil bangun tidur", "Albert einstein", "10 menit");
        ItemBeritaPopuler ibp2 = new ItemBeritaPopuler(R.drawable.gambarberita, "29 februari 20024", "seorang anak berhasil bangun tidur", "Albert einstein", "10 menit");
        ItemBeritaPopuler ibp3 = new ItemBeritaPopuler(R.drawable.gambarberita, "29 februari 20024", "seorang anak berhasil bangun tidur", "Albert einstein", "10 menit");
        adapterBeritaPopulerList.add(ibp);
        adapterBeritaPopulerList.add(ibp1);
        adapterBeritaPopulerList.add(ibp2);
        adapterBeritaPopulerList.add(ibp3);

    }

    //kode ini tambahaan unutk meminndahkan fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Mengatur onClickListener untuk tombol buttonRekom
        view.findViewById(R.id.buttonRekom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).goToCariFragment(); // Panggil metode navigasi ke CariFragment
            }
        });

        view.findViewById(R.id.buttonPopuler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode navigasi ke BeritaFragment saat tombol ditekan
                goToBeritaFragment();
            }
        });


    }
    private void goToBeritaFragment() {
        BeritaFragment beritaFragment = new BeritaFragment(); // Buat instance BeritaFragment
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, beritaFragment) // Ganti Fragment saat ini dengan BeritaFragment
                .addToBackStack(null) // Tambahkan transaksi ke back stack
                .commit(); // Lakukan transaksi Fragment
    }


    //untuk button notif
    private void pindahKeHalamanNotifikasi() {
        Intent intent = new Intent(getActivity(), ActivityNotifikasi.class);
        startActivity(intent);
    }


}

