package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class afterclickingdonarid extends AppCompatActivity {
    TextView sname,semail,saddhar,smobile,sblood_gp,sunits_donate,saddress;

//    private static String url ="https://soorveer-api.herokuapp.com/api/donar/";

//    private static String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar/";

    private static String url ="https://blood-camp-api-production.up.railway.app/api/donar/";
    String reg1 = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterclickingdonarid);
        sname=findViewById(R.id.sname);
        semail=findViewById(R.id.semail);
        saddhar=findViewById(R.id.saddhar);
        smobile=findViewById(R.id.smobile);
        sblood_gp=findViewById(R.id.sblood_gp);
        sunits_donate=findViewById(R.id.sunits_donate);
        saddress=findViewById(R.id.saddress);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            reg1=bundle.getString("id");

        }

        getData();



    }


    public void getData()
    {
        ProgressDialog progress;
        progress=ProgressDialog.show(this,"Loading","",true);

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+reg1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject1 = new JSONObject(response);
                    progress.dismiss();
                    sname.setText(jsonObject1.getString("donarname"));
                    semail.setText(jsonObject1.getString("email"));
                    saddhar.setText(jsonObject1.getString("aadhar"));
                    smobile.setText(jsonObject1.getString("mobile"));
                    sblood_gp.setText(jsonObject1.getString("bloodGroup"));
                    sunits_donate.setText(jsonObject1.getString("times"));
                    saddress.setText(jsonObject1.getString("address"));

                    Log.i("data :",response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
            }


        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}