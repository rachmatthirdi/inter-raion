package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BerandaFragment extends Fragment {
    private AdapterRekomGuru adapterRekomGuru;
    private ImageButton buttonNotification;
    private MyAdapter adapter;
    private FirebaseFirestore db;
    private ArrayList<dbBerita> dbBeritaArrayList;
    private RecyclerView rowRecyclerView;
    private RecyclerView rowRecyclerViewBerita;
    private List<ItemRekomendasiGuru> adapterRekomGuruList = new ArrayList<>();
    private List<ItemBeritaPopuler> adapterBeritaPopulerList = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_beranda, container, false);
        addRekomGuru();
        rowRecyclerView = view.findViewById(R.id.recycle_rekom_guru);
        db = FirebaseFirestore.getInstance();
        dbBeritaArrayList = new ArrayList<dbBerita>();
        adapter = new MyAdapter(getContext(),dbBeritaArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rowRecyclerView.setLayoutManager(layoutManager);
        adapterRekomGuru = new AdapterRekomGuru(this, adapterRekomGuruList);
        rowRecyclerView.setAdapter(adapterRekomGuru);
        rowRecyclerView.setNestedScrollingEnabled(true);


//        addBeritaPopular();
        rowRecyclerViewBerita = view.findViewById(R.id.recycle_populer_berita);
        LinearLayoutManager layoutManagerBerita = new LinearLayoutManager(requireContext());
        layoutManagerBerita.setOrientation(LinearLayoutManager.VERTICAL);
        AdapterBeritaPopuler adapterRekomGuruBerita = new AdapterBeritaPopuler(new BerandaFragment(), adapterBeritaPopulerList);
        rowRecyclerViewBerita.setLayoutManager(layoutManagerBerita);
        rowRecyclerViewBerita.setAdapter(adapter);
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
        EventChangeListener();
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
    // harus ganti ke ActivityNotifikasi
    private void pindahKeHalamanNotifikasi() {
        Intent intent = new Intent(getActivity(), ActivityProfileGuru.class);
        startActivity(intent);
    }

    private void EventChangeListener(){
        db.collection("berita").orderBy("judul", Query.Direction.ASCENDING).
                addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error!=null){

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()== DocumentChange.Type.ADDED){
                                dbBeritaArrayList.add(dc.getDocument().toObject(dbBerita.class));
                            }

                            adapter.notifyDataSetChanged();

                        }

                    }
                });
    }


}

