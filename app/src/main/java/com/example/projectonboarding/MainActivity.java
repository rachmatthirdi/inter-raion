package com.example.projectonboarding;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    BeritaFragment beritaFragment= new BeritaFragment();
    BerandaFragment berandaFragment= new BerandaFragment();
    CariFragment cariFragment= new CariFragment();
    ProfileFragment profileFragment= new ProfileFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BerandaFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.nav_beranda) {
                    selectedFragment = new BerandaFragment();
                } else if (item.getItemId() == R.id.nav_cari) {
                    selectedFragment = new CariFragment();
                } else if (item.getItemId() == R.id.nav_berita) {
                    selectedFragment = new BeritaFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });

        //ini juga unutk  rekomendasi guru

        // Tampilkan BerandaFragment saat Activity dimulai
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new BerandaFragment())
                    .commit();
        }

    }

    // ini untuk rekomendasi guru


    // Metode untuk berpindah ke CariFragment
    public void goToCariFragment() {
        CariFragment cariFragment = new CariFragment(); // Buat instance CariFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, cariFragment); // Ganti Fragment saat ini dengan CariFragment
        transaction.addToBackStack(null); // Tambahkan transaksi ke back stack
        transaction.commit(); // Lakukan transaksi
    }
}