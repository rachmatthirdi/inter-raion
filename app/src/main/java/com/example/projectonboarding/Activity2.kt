package com.example.projectonboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Activity2 : AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        btnLanjutkan = findViewById(R.id.button)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@Activity2, Activity3::class.java))
        }


    }

}