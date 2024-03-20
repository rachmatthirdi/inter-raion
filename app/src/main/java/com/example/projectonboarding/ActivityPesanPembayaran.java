package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPesanPembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_pembayaran);

        // Inisialisasi button
        Button buttonBayarPembayaran = findViewById(R.id.buttonBayarPembayaran);


        // Menambahkan OnClickListener pada Button
        buttonBayarPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat Intent untuk pindah ke ActivityPesanPembayaran
                Intent intent = new Intent(ActivityPesanPembayaran.this, ActivityPembayaranLanjutan.class);
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