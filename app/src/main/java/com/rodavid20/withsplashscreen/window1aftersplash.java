package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class window1aftersplash extends AppCompatActivity {
     Button createngo,get_qr;
     TextView createnewuser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window1aftersplash);
        get_qr=findViewById(R.id.get_qr);
        createngo=findViewById(R.id.NGO);
        createnewuser=findViewById(R.id.newuser);
        ///new button added///
        get_qr.setOnClickListener(View->{
            Intent intent=new Intent(window1aftersplash.this,Get_QRCode.class);
            startActivity(intent);
        });

        createngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createintentngo=new Intent(window1aftersplash.this,Login_page.class);
                startActivity(createintentngo);
            }
        });
        createnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createintentnewuser=new Intent(window1aftersplash.this,Ngodetails.class);
                startActivity(createintentnewuser);
            }
        });
    }
    @Override
    public void onBackPressed() {

        return;
    }
}