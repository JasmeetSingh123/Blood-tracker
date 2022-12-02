package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class window2aftersplash extends AppCompatActivity {
    public Button existinguserbtn,createcamp;
    ImageButton logoutbtn;
    String ngoID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window2aftersplash);
        existinguserbtn=findViewById(R.id.existing);
        createcamp=findViewById(R.id.createcamp);
        logoutbtn=findViewById(R.id.logoutBtn);
        Intent intent1 = getIntent();
        ngoID = intent1.getStringExtra("ngo_id");
        Toast.makeText(this, "ngo_id"+ngoID, Toast.LENGTH_SHORT).show();

        logoutbtn.setOnClickListener(v->{
            Intent intent2 = new Intent(window2aftersplash.this,window1aftersplash.class);
            startActivity(intent2);
        });

        createcamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View  view) {
                Intent createintentcreatecamp=new Intent(window2aftersplash.this,createcamp.class);
                createintentcreatecamp.putExtra("ngo_id",ngoID);
                startActivity(createintentcreatecamp);
            }
        });
        existinguserbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createintentexisting=new Intent(window2aftersplash.this,Existingcamp.class);
                createintentexisting.putExtra("ngo_id",ngoID);
                startActivity(createintentexisting);
            }
        });
    }
    @Override
    public void onBackPressed() {

        return;
    }
}