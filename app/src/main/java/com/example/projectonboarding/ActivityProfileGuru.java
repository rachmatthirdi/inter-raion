package com.example.projectonboarding;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityProfileGuru extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    InformasiFragment informasiFragment = new InformasiFragment();
    UlasanFragment ulasanFragment = new UlasanFragment();
    JadwalFragment jadwalFragment = new JadwalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_guru);

        bottomNavigationView = findViewById(R.id.nav_profile_guru);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, new InformasiFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_info) {
                    selectedFragment = new InformasiFragment();
                } else if (item.getItemId() == R.id.nav_ulas) {
                    selectedFragment = new UlasanFragment();
                } else if (item.getItemId() == R.id.nav_jadwal) {
                    selectedFragment = new JadwalFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, selectedFragment).commit();
                return true;
            }
        });

        // Tampilkan InformasiFragment saat Activity dimulai
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_profile, new InformasiFragment())
                    .commit();
        }


        ImageButton backButton = findViewById(R.id.imageButtonBackProfileGuru);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke fragment sebelumnya
                onBackPressed();
            }
        });
    }
}

