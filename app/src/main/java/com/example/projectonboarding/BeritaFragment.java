package com.example.projectonboarding;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeritaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeritaFragment extends Fragment implements MyAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ArrayList<dbBerita> dbBeritaArrayList;
    private MyAdapter myAdapter;
    private FirebaseFirestore db;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardView berita1;

    public BeritaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeritaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeritaFragment newInstance(String param1, String param2) {
        BeritaFragment fragment = new BeritaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_berita,container,false);


       recyclerView = view.findViewById(R.id.rv_berita);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       db = FirebaseFirestore.getInstance();
       dbBeritaArrayList = new ArrayList<dbBerita>();
       myAdapter = new MyAdapter(getContext(),dbBeritaArrayList,this);
        recyclerView.setAdapter(myAdapter);
        EventChangeListener();





       return view;
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
                        myAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

    @Override
    public void onItemClick(int position) {
        dbBerita berita = dbBeritaArrayList.get(position);
        String judul = berita.getJudul();
        String tanggal = berita.getTanggal();
        String author = berita.getAuthor();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        BeritaFragmentDetail detailFragment = new BeritaFragmentDetail();
        Bundle bundle = new Bundle();
        bundle.putString("judul_berita", judul);
        bundle.putString("tanggal_berita", tanggal);
        bundle.putString("author_berita", author);
        detailFragment.setArguments(bundle);
        transaction.replace(R.id.FragmentBerita, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}