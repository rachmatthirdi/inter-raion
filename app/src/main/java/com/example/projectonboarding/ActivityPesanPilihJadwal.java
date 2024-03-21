package com.example.projectonboarding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ActivityPesanPilihJadwal extends AppCompatActivity {

    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_pilih_jadwal);

        constraintLayout = findViewById(R.id.textTanggalPesan);

        ImageButton backButton = findViewById(R.id.imageButtonBackProfileGuru);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kembali ke activity sebelumnya
                onBackPressed();
            }
        });

        TextView textBulanan = findViewById(R.id.textBulanan);
        // Menambahkan OnClickListener pada TextView
        textBulanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat dan menampilkan dialog fragment
                OverlayDialogFragment dialogFragment = new OverlayDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "OverlayDialogFragment");

                // Menampilkan ConstraintLayout saat TextView textBulanan ditekan
                constraintLayout.setVisibility(View.VISIBLE);
            }
        });

        // Mendapatkan referensi Button
        Button buttonSelanjutnya = findViewById(R.id.buttonSelanjutnyaPilihjadwal);

        // Menambahkan OnClickListener pada Button
        buttonSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menghilangkan tampilan ConstraintLayout saat tombol "Selanjutnya" ditekan
                constraintLayout.setVisibility(View.GONE);

                // Membuat Intent untuk pindah ke activity_pesan_review
                Intent intent = new Intent(ActivityPesanPilihJadwal.this, ActivityPesanReview.class);
                startActivity(intent);
            }
        });
    }
}
