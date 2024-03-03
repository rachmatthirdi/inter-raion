package com.example.projectonboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button  btnLogin, btnLoginAdmin;
    private TextView btnRegist;
    private DatabaseReference database, databaseAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmailLog);
        etPassword = findViewById(R.id.etPassword);
        btnRegist = findViewById(R.id.btnRegistLog1);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  keRegist = new Intent(getApplicationContext(), Register.class);
                startActivity(keRegist);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                database = FirebaseDatabase.getInstance().getReference("users");
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                } else {

                    Query query = database.orderByChild("email").equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                                    if (userSnapshot.child("password").getValue(String.class).equals(password)) {
                                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                        Intent mainApp = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(mainApp);
                                        return;
                                    }
                                }

                                Toast.makeText(getApplicationContext(), "Password salah", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(getApplicationContext(), "Email tidak ditemukan", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAdmin = etEmail.getText().toString();
                String passwordAdmin = etPassword.getText().toString();
                databaseAdmin = FirebaseDatabase.getInstance().getReference("admin");
                if (emailAdmin.isEmpty()||passwordAdmin.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Username atau Passowrd salah!",Toast.LENGTH_SHORT).show();
                } else {
                    Query query = databaseAdmin.orderByChild("email").equalTo(emailAdmin);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {

                                    if (userSnapshot.child("password").getValue(String.class).equals(passwordAdmin)) {
                                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                        Intent mainApp = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(mainApp);
                                        return;
                                    } else Toast.makeText(getApplicationContext(),"Password salah",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"Data belum ada",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}