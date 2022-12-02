package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView wel;
    ImageView img;
    private static int splashtimeout=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wel=findViewById(R.id.Textview1);
//        save=findViewById(R.id.Textview2);
        img=findViewById(R.id.image);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent=new Intent(MainActivity.this,window1aftersplash.class);
                startActivity(splashintent);
                finish();
            }
        },splashtimeout);

        Animation myanim1= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation2);
        wel.startAnimation(myanim1);
        Animation myanim2= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation1);
        img.startAnimation(myanim2);
//        Animation myanim3= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation2);
//        save.startAnimation(myanim3);


    }
}