package com.example.projectonboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LainnyaPopuler: AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_beranda)

        btnLanjutkan = findViewById(R.id.buttonPopuler)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@LainnyaPopuler, BeritaFragment::class.java))
        }

    }
}