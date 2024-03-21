package com.example.projectonboarding;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.squareup.picasso.Picasso;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityProfileGuru extends AppCompatActivity {

    private TextView nama,matpel,jenjang1,jenjang2,rating;
    private ImageView foto;
    BottomNavigationView bottomNavigationView;
    InformasiFragment informasiFragment = new InformasiFragment();
    UlasanFragment ulasanFragment = new UlasanFragment();
    JadwalFragment jadwalFragment = new JadwalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_guru);
        nama = findViewById(R.id.textNamaGuruProfile);
        matpel = findViewById(R.id.textMapelGuruProfile);
        jenjang1 = findViewById(R.id.textGolongan1);
        jenjang2 = findViewById(R.id.textGolongan2);
        foto = findViewById(R.id.gambar_guru_besar);
        rating = findViewById(R.id.rating_guru);
        Bundle bundle = getIntent().getExtras();
        String namaGuru = bundle.getString("nama_guru");
        String matpelGuru = bundle.getString("matpel_guru");
        String jenjang1Guru = bundle.getString("jenjang1_guru");
        String jenjang2Guru = bundle.getString("jenjang2_guru");
        String ratingGuru = bundle.getString("rating_guru");
        String fotoGuru = bundle.getString("foto_guru");
        nama.setText(namaGuru);
        matpel.setText(matpelGuru);
        jenjang1.setText(jenjang1Guru);
        jenjang2.setText(jenjang2Guru);
        rating.setText(ratingGuru);
        Picasso.get().load(fotoGuru).into(foto);
        bottomNavigationView = findViewById(R.id.nav_profile_guru);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, informasiFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_info) {
                    selectedFragment = informasiFragment;
                } else if (item.getItemId() == R.id.nav_ulas) {
                    selectedFragment = ulasanFragment;
                } else if (item.getItemId() == R.id.nav_jadwal) {
                    selectedFragment = jadwalFragment;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, selectedFragment).commit();
                return true;
            }
        });

        ImageButton backButton = findViewById(R.id.imageButtonBackProfileGuru);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


            ImageButton buttonMessage = findViewById(R.id.buttonMessage);
            buttonMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Membuat Intent untuk pindah ke ActivityPesanPilihJadwal
                    Intent intent = new Intent(ActivityProfileGuru.this, ActivityPesanPilihJadwal.class);
                    startActivity(intent);
                }
            });
    }
}




