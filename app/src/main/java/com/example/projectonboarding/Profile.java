package com.example.projectonboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
private Spinner spnPendidikan, spnDomisili;
private String[] pendidikan,domisili;
private Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        spnPendidikan = findViewById(R.id.spn_pendididkan1);
        spnDomisili = findViewById(R.id.spn_domisili);
        pendidikan = getResources().getStringArray(R.array.pendidikan);
        domisili = getResources().getStringArray(R.array.domisili);
        simpan = findViewById(R.id.btnEditSimpan);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.pendidikan
        , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPendidikan.setAdapter(adapter);
        spnPendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.domisili
        , android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDomisili.setAdapter(adapter2);
        spnDomisili.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent nxt = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(nxt);
            }
        });
    }
}