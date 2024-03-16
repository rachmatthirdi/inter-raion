package com.example.projectonboarding;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button logout,logoutfix,batal,btnEditProfile;
    private FirebaseAuth auth;
    private TextView tvUser, tvEmailUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = rootView.findViewById(R.id.logout1);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user= auth.getCurrentUser();
        tvUser = rootView.findViewById(R.id.tv_namaUser);
        tvEmailUser = rootView.findViewById(R.id.tv_emailUser);
        tvUser.setText(user.getDisplayName());
        tvEmailUser.setText(user.getEmail());
        btnEditProfile = rootView.findViewById(R.id.btn_profile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update = new Intent(getContext(), EditProfile.class);
                startActivity(update);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = getLayoutInflater().inflate(R.layout.logout_popup,null);
                Button btn_logoutfix = popup.findViewById(R.id.btn_logoutfix);
                Button cancel = popup.findViewById(R.id.btn_batal);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(popup);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                btn_logoutfix.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        auth.signOut();
                        Intent back = new Intent(getActivity(),Login.class);
                        startActivity(back);

                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


            }
        });

        return rootView;
    }


}