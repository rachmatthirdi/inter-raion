package com.example.projectonboarding;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ActivityBarProfileGuru extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    InformasiFragment informasiFragment = new InformasiFragment();
    UlasanFragment ulasanFragment = new UlasanFragment();
    JadwalFragment jadwalFragment = new JadwalFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_profile_guru);

        bottomNavigationView = findViewById(R.id.nav_profile_guru);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, informasiFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_beranda) {
                    selectedFragment = informasiFragment;
                } else if (item.getItemId() == R.id.nav_cari) {
                    selectedFragment = ulasanFragment;
                } else if (item.getItemId() == R.id.nav_berita) {
                    selectedFragment = jadwalFragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_profile, selectedFragment).commit();
                return true;
            }
        });

        // Tampilkan InformasiFragment saat Activity dimulai
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_profile, informasiFragment)
                    .commit();
        }
    }
}

