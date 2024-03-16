package com.example.projectonboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeritaFragmentDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeritaFragmentDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FirebaseFirestore db;
    TextView tvJudul,tvAuthor,tvTanggal,tvIsi;

    public BeritaFragmentDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeritaFragmentDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static BeritaFragmentDetail newInstance(String param1, String param2) {
        BeritaFragmentDetail fragment = new BeritaFragmentDetail();
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
       View view = inflater.inflate(R.layout.fragment_berita_detail,container,false);
        String judulBerita = getArguments().getString("judul_berita");
        String tanggalBerita = getArguments().getString("tanggal_berita");
        String authorBerita = getArguments().getString("author_berita");
        tvJudul = view.findViewById(R.id.tvJudulDetail);
        tvAuthor = view.findViewById(R.id.tvAuthorDetail);
        tvTanggal = view.findViewById(R.id.tvTanggalDetail);
        tvIsi = view.findViewById(R.id.tvIsiBerita);
        tvJudul.setText(judulBerita);
        tvAuthor.setText(authorBerita);
        tvTanggal.setText(tanggalBerita);
        db = FirebaseFirestore.getInstance();
        Query query = db.collection("berita").whereEqualTo("author", authorBerita);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    QuerySnapshot querySnapshot = task.getResult();


                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {

                        String isiBerita = document.getString("isi");
                        tvIsi.setText(Html.fromHtml(isiBerita));
                    }
                } else {

                }
            }
        });

       return view;
    }
}