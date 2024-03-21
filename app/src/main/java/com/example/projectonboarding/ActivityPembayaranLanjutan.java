package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPembayaranLanjutan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_lanjutan);

        // Inisialisasi button
        Button buttonSudahBayar = findViewById(R.id.buttonSudahBayar);

        // Menambahkan OnClickListener pada Button
        buttonSudahBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke ActivityPesanPembayaran
                Intent intent = new Intent(ActivityPembayaranLanjutan.this, ActivityPembayaranBerhasil.class);
                startActivity(intent);
            }
        });

        ImageView panahPilih1 = findViewById(R.id.panahPilih1);

        // Menambahkan OnClickListener pada ImageView
        panahPilih1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke ActivityFAQs
                Intent intent = new Intent(ActivityPembayaranLanjutan.this, ActivityFAQsBNI.class);
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