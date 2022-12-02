package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Existingcamp extends AppCompatActivity {
    public Button getid,getlist,createdonar;
    public String ngoID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existingcamp);
        Intent intent = getIntent();
        ngoID = intent.getStringExtra("ngo_id");
        Toast.makeText(this, "ngo_id"+ngoID, Toast.LENGTH_SHORT).show();
        getid = findViewById(R.id.getid);
        getlist = findViewById(R.id.getlist);
        getlist.setOnClickListener(view -> {
            Intent intent1=new Intent(Existingcamp.this,camplist.class);
            intent1.putExtra("ngo_id",ngoID);
            startActivity(intent1);
        });
        getid.setOnClickListener(view -> {
            Intent intent2=new Intent(Existingcamp.this,campid.class);
            intent2.putExtra("ngo_id",ngoID);
            startActivity(intent2);
        });
    }


}