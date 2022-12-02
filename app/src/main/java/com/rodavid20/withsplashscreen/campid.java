package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class campid extends AppCompatActivity {
    ImageView searchbtn;
    EditText campid;
    String ngoID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campid);
        Intent intent = getIntent();
        ngoID= intent.getStringExtra("ngo_id");
        Toast.makeText(this, "ngo_id"+ngoID, Toast.LENGTH_SHORT).show();
        campid=findViewById(R.id.cid);
        searchbtn=findViewById(R.id.search);
        searchbtn.setOnClickListener(view -> {
            String camp_id = campid.getText().toString();
            Intent intent1=new Intent(campid.this,aftercampid.class);
            intent1.putExtra("ngo_id",ngoID);
            intent1.putExtra("camp_id",camp_id);
            startActivity(intent1);
        });
    }


}
