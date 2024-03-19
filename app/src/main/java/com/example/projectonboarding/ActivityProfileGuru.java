package com.example.projectonboarding;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.ColorRes;
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
    }
}




