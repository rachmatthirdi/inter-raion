package com.example.projectonboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity5: AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)

        btnLanjutkan = findViewById(R.id.button4)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@Activity5, Login::class.java)) //kembali ke sebelumnya
        }
    }
}