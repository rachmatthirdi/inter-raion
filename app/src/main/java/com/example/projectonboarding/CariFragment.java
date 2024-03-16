package com.example.projectonboarding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
 * Use the {@link CariFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CariFragment extends Fragment implements guruAdapter.OnItemClickListener{
    RecyclerView recyclerView;
    ArrayList<dbGuru> dbGuruArrayList;
    guruAdapter guruAdapter;
    FirebaseFirestore db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CariFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CariFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CariFragment newInstance(String param1, String param2) {
        CariFragment fragment = new CariFragment();
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
       View view = inflater.inflate(R.layout.fragment_cari,container,false);
       recyclerView = view.findViewById(R.id.rvGuru);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
       db = FirebaseFirestore.getInstance();
       dbGuruArrayList = new ArrayList<dbGuru>();
      guruAdapter = new guruAdapter(getContext(),dbGuruArrayList,this);
      recyclerView.setAdapter(guruAdapter);
        OnEventChangeListener();
       return view;
    }
    private void OnEventChangeListener(){
        db.collection("guru").orderBy("foto", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType()== DocumentChange.Type.ADDED){
                                dbGuruArrayList.add(dc.getDocument().toObject(dbGuru.class));
                            }
                            guruAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    @Override
    public void onItemClick(int position) {

    }
}