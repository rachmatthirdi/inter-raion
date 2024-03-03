package com.example.projectonboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity3: AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        btnLanjutkan = findViewById(R.id.button2)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@Activity3, Activity4::class.java))
        }

    }
}