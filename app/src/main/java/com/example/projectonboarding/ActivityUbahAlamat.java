package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityUbahAlamat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_alamat);

        // Mendapatkan referensi Button
        Button buttonKonfirmasiReview = findViewById(R.id.buttonSimpanAlamat);

        // Menambahkan OnClickListener pada Button
        buttonKonfirmasiReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke ActivityPesanPembayaran
                Intent intent = new Intent(ActivityUbahAlamat.this, ActivityPesanReview.class);
                startActivity(intent);
            }
        });

        ImageButton backButton = findViewById(R.id.imageButtonBackReview);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke activity sebelumnya
                onBackPressed();
            }
        });

    }
}