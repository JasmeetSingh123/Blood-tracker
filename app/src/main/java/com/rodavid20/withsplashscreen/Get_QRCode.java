package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Get_QRCode extends AppCompatActivity {
    ImageView qsearch;
    EditText qsample;
    String receivedDonar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_qrcode);
        qsearch=findViewById(R.id.qsearch);
        qsample=findViewById(R.id.qsample_id);


        qsearch.setOnClickListener(view -> {
            receivedDonar=qsample.getText().toString();
            Intent intent = new Intent(Get_QRCode.this,getQrgenerator.class);
            intent.putExtra("donar_id",receivedDonar);

            startActivity(intent);

        });
    }
}