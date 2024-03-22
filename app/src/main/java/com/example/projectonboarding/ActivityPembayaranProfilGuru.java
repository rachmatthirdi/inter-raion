package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPembayaranProfilGuru extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_profil_guru);

        // Inisialisasi button
        Button buttonBayarPembayaran = findViewById(R.id.buttonPembayaranProfilGuru);


        // Menambahkan OnClickListener pada Button
        buttonBayarPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke ActivityPesanPembayaran
                Intent intent = new Intent(ActivityPembayaranProfilGuru.this, ActivityAturJadwalProfilGuru.class);
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