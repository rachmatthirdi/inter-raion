package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.time.Duration;
import java.util.regex.Pattern;

public class Forgot extends AppCompatActivity {
    private EditText etEmailForgot;
    private Button btnNext;
    private ImageView btnBack;
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        etEmailForgot = findViewById(R.id.etEmailForgot);
        auth = FirebaseAuth.getInstance();
        btnNext = findViewById(R.id.btnNext);
        btnBack = findViewById(R.id.btnBack);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailForgot.getText().toString();
                if (email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"E-mail masih kosong", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)){
                    Toast.makeText(getApplicationContext(),"Masukkan E-mail dengan benar",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent next = new Intent(getApplicationContext(), CheckMail.class);
                                startActivity(next);
                            }
                            else Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(getApplicationContext(),Login.class);
                startActivity(kembali);
            }
        });
    }




}