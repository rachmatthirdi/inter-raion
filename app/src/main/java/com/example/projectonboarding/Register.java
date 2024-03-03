package com.example.projectonboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText etUsername, etPassword, etEmail, etConfirmPassword;
    private Button btnRegister;
    private DatabaseReference database;
    private ImageView arrowBack;
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.etEmailLog);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnRegister = findViewById(R.id.btnRegistLog);
        arrowBack = findViewById(R.id.ivArrow);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        database = FirebaseDatabase.getInstance().getReferenceFromUrl(
                "https://loginregist-ea208-default-rtdb.firebaseio.com/");

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keLogin = new Intent (getApplicationContext(), Login.class);
                startActivity(keLogin);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (username.isEmpty()||email.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ada data yang masih kosong",Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)){
                    Toast.makeText(getApplicationContext(),"Password tidak sama!",Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)){
                    Toast.makeText(getApplicationContext(),"E-mail tidak valid",Toast.LENGTH_SHORT).show();
                }
                else {
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("password").setValue(password);
                    Toast.makeText(getApplicationContext(),"Register Berhasil",Toast.LENGTH_SHORT).show();
                    Intent regist = new Intent(getApplicationContext(), Login.class);
                    startActivity(regist);
                }
            }
        });
    }
}