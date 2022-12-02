package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class aftercamplistdesign extends AppCompatActivity {
    ImageView img;
    TextView tv1,tv2,tv3;
    String camp_id;
    Button createdonarbtn,existingdonarbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftercamplistdesign);
        createdonarbtn=findViewById(R.id.createdonar);
        existingdonarbtn=findViewById(R.id.existingdonar);


        img=(ImageView) findViewById(R.id.afterclickimg);
        tv1=(TextView) findViewById(R.id.afterclicktext);
        tv2=(TextView) findViewById(R.id.afterclickmob);
        tv3=(TextView) findViewById(R.id.afterclickadd);

        img.setImageResource(getIntent().getIntExtra("imageView",0));
        tv1.setText(getIntent().getStringExtra("textview1"));
        tv2.setText(getIntent().getStringExtra("textview2"));
        tv3.setText(getIntent().getStringExtra("textview3"));

        camp_id=tv1.getText().toString();

        createdonarbtn.setOnClickListener(view -> {
            Intent intent=new Intent(aftercamplistdesign.this,donardetails.class);
            intent.putExtra("camp_id",camp_id);
            intent.putExtra("location",tv2.getText().toString());
            startActivity(intent);
        });
        existingdonarbtn.setOnClickListener(view -> {
            Intent intent=new Intent(aftercamplistdesign.this,afterclickingexistingdonar.class);
            intent.putExtra("camp_id",camp_id);
            startActivity(intent);
        });



    }

}