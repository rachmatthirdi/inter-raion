package com.example.projectonboarding;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private RecyclerView recyclerView;
    private Button filter,cari;
    private ArrayList<dbGuru> dbGuruArrayList;
    private ArrayList<dbGuru> dbGuruArrayListCopy;
    private guruAdapter guruAdapter;
    private FloatingActionButton fab;
    private FirebaseFirestore db;

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
       filter = view.findViewById(R.id.btnFilter);
       recyclerView = view.findViewById(R.id.rvGuru);
        cari = view.findViewById(R.id.button6);
       recyclerView.setHasFixedSize(true);
        fab = view.findViewById(R.id.floatingActionButton);
       recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
       db = FirebaseFirestore.getInstance();
       dbGuruArrayList = new ArrayList<dbGuru>();
      guruAdapter = new guruAdapter(getContext(),dbGuruArrayList,this);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getContext(), Chat.class);
              startActivity(intent);
          }
      });
      recyclerView.setAdapter(guruAdapter);
        OnEventChangeListener();
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dbGuruArrayList.equals(dbGuruArrayListCopy)){
                    resetGuru();
                }
                showFilter();
            }
        });
        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dbGuruArrayList.equals(dbGuruArrayListCopy)){
                    resetGuru();
                }
                View popup = getLayoutInflater().inflate(R.layout.searchview_guru,null);
                SearchView sv = popup.findViewById(R.id.searchViewGuru);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(popup);
                AlertDialog alertDialog = builder.create();
                int[] location = new int[2];
                cari.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];
                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
                params.gravity = Gravity.TOP | Gravity.START;
                params.x = x;
                params.y = y + cari.getHeight();
                alertDialog.getWindow().setAttributes(params);
                alertDialog.show();
                sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchRecycleView(newText);
                        return true;
                    }
                });
            }
        });
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
                        dbGuruArrayListCopy = new ArrayList<>(dbGuruArrayList);

                    }
                });
    }

    @Override
    public void onItemClick(int position) {

    }
    private void showFilter(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.filter_guru,null);
        Spinner spnJenjang = view.findViewById(R.id.spnJenjang);
        Spinner spnMatpel = view.findViewById(R.id.spnMatpel);
        Button batal = view.findViewById(R.id.button7);
        Button simpan = view.findViewById(R.id.button8);
        ArrayAdapter<CharSequence> jenjangAdapter = ArrayAdapter.createFromResource(getContext(),R.array.jenjang, android.R.layout.simple_spinner_item);
        jenjangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnJenjang.setAdapter(jenjangAdapter);
        ArrayAdapter<CharSequence> matpelAdapter = ArrayAdapter.createFromResource(getContext(),R.array.matpel, android.R.layout.simple_spinner_item);
        jenjangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMatpel.setAdapter(matpelAdapter);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        final String[] matpel = {""};
        final String[] jenjang = {""};
        spnJenjang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jenjang[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnMatpel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matpel[0] = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterRecyclerView(matpel[0],jenjang[0] );
                alertDialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGuru();
                alertDialog.dismiss();
            }
        });
    }
    private void filterRecyclerView(String selectedMatpel, String selectedJenjang) {
        ArrayList<dbGuru> filteredList = new ArrayList<>();
        for (dbGuru guru : dbGuruArrayList) {
            if (guru.getMatpel().equals(selectedMatpel) && (guru.getJenjang1().equals(selectedJenjang) || guru.getJenjang2().equals(selectedJenjang))) {
                filteredList.add(guru);
            }
        }

        dbGuruArrayList.clear();
        dbGuruArrayList.addAll(filteredList);
        guruAdapter.notifyDataSetChanged();
    }
    private void resetGuru(){
        dbGuruArrayList.clear();
        dbGuruArrayList.addAll(dbGuruArrayListCopy);
        guruAdapter.notifyDataSetChanged();
    }
    private void searchRecycleView(String nama){
        ArrayList<dbGuru> filteredList = new ArrayList<>();
        for (dbGuru guru : dbGuruArrayList){
            if (guru.getnama().toLowerCase().contains(nama)){
                filteredList.add(guru);

            }
        }
        dbGuruArrayList.clear();
        dbGuruArrayList.addAll(filteredList);
        guruAdapter.notifyDataSetChanged();
    }
}