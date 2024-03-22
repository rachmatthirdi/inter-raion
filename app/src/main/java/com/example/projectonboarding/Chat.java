package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Chat extends AppCompatActivity {
    private CardView cv1,cv2;
    private ImageButton back;
    private TextView guru1,guru2;
    private ImageView g1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        guru1 = findViewById(R.id.chatguru1);
        guru2 = findViewById(R.id.chatguru2);
        back = findViewById(R.id.imageButton4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
       cv1 = findViewById(R.id.cardGuru3);
       cv1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String namaGuru1 = guru1.getText().toString();
               Intent intent = new Intent(getApplicationContext(), Chat1.class);
               intent.putExtra("Guru",namaGuru1);
               startActivity(intent);
           }
       });
       cv1 = findViewById(R.id.cardView);
       cv1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                String namaGuru = guru2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Chat1.class);
                intent.putExtra("Guru",namaGuru);
                startActivity(intent);
           }
       });
    }
}