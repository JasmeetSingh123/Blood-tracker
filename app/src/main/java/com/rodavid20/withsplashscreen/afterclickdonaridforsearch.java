package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class afterclickdonaridforsearch extends AppCompatActivity {
    ImageView searchbtn2;
    EditText ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterclickdonaridforsearch);
        searchbtn2=findViewById(R.id.search2);
        ed=findViewById(R.id.campid);
        searchbtn2.setOnClickListener(view -> {
            Intent intent=new Intent(afterclickdonaridforsearch.this,afterclickingdonarid.class);
            intent.putExtra("id",ed.getText().toString());
            startActivity(intent);
        });
    }
}