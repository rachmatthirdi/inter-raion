package com.example.projectonboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Mengatur tata letak splash screen

        // Tambahkan kode lain yang diperlukan di sini
        // Misalnya, mengatur durasi tampilan splash screen
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, Activity2::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
