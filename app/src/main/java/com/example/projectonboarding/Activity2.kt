package com.example.projectonboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class Activity2 : AppCompatActivity() {
    private var btnLanjutkan: Button? = null
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user!=null){
            val main = Intent(this,MainActivity::class.java)
            startActivity(main)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        btnLanjutkan = findViewById(R.id.button)
        btnLanjutkan?.setOnClickListener {
            startActivity(Intent(this@Activity2, Activity3::class.java))
        }


    }

}