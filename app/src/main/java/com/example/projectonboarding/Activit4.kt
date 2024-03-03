package com.example.projectonboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity4 : AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4)

        btnLanjutkan = findViewById(R.id.button3)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@Activity4, Activity5::class.java))
        }
    }
}