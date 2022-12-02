package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class afterclickingexistingdonar extends AppCompatActivity {
    Button donaridbtn,donarlistbtn;
    String camp_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterclickingexistingdonar);
        Intent intent1 = getIntent();
        camp_id=intent1.getStringExtra("camp_id");
        donaridbtn=findViewById(R.id.donarid);
        donarlistbtn=findViewById(R.id.donarlist);
        donaridbtn.setOnClickListener(view -> {
            Intent intent=new Intent(afterclickingexistingdonar.this,afterclickdonaridforsearch.class);
            intent.putExtra("camp_id",camp_id);
            startActivity(intent);
        });
        donarlistbtn.setOnClickListener(view -> {
            Intent intent=new Intent(afterclickingexistingdonar.this,afterclickdonarlist.class);
            intent.putExtra("camp_id",camp_id);
            startActivity(intent);
        });
    }
}