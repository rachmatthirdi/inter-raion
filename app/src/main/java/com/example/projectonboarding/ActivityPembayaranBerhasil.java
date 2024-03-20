package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class ActivityPembayaranBerhasil extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_berhasil);


        // Mendapatkan referensi Button
        Button buttonKembali = findViewById(R.id.buttonKembaliBerhasilPesan);

        // Menambahkan OnClickListener pada Button
        buttonKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke MainActivity
                Intent intent = new Intent(ActivityPembayaranBerhasil.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = findViewById(R.id.imageButtonBackNotif);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke activity sebelumnya
                onBackPressed();
            }
        });
    }
}


