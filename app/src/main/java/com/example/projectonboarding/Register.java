package com.example.projectonboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText etUsername, etPassword, etEmail, etConfirmPassword;
    private Button btnRegister;
    private DatabaseReference database;
    private ImageView arrowBack;
    private FirebaseAuth auth;
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
        auth = FirebaseAuth.getInstance();

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent keLogin = new Intent (getApplicationContext(), Profile.class);
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
                   auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Pendaftaran Berhasil",Toast.LENGTH_SHORT).show();
                               FirebaseUser user = auth.getCurrentUser();
                               UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                       .setDisplayName(username).build();
                                Intent sign = new Intent(getApplicationContext(),Profile.class);
                                startActivity(sign);
                           }
                       }
                   });
                }
            }
        });
    }
}