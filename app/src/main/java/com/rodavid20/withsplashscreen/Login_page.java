package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login_page extends AppCompatActivity {
    EditText ed1,ed2;
    Button loginbtn;
    String User="", password="";


//    public static String url = "https://soorveer-api.herokuapp.com/api/ngo/";

    public static String url = "https://blood-camp-api-production.up.railway.app/api/ngo/";

//    public static String url = "https://dull-teal-dolphin-coat.cyclic.app/api/ngo/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ed1=findViewById(R.id.ngo_un_id);
        ed2=findViewById(R.id.pass);
        loginbtn=findViewById(R.id.button3);
        loginbtn.setOnClickListener(view -> {
            Log.i("inf1 ", ed2.getText().toString());
            Log.i("inf2 ", ed1.getText().toString());

            if(ed1.getText().toString().equals("") || ed2.getText().toString().equals("")){

                Toast.makeText(this, "please fill details", Toast.LENGTH_SHORT).show();
            }
            else{
                getData();
            }
        });
    }


    public void  getData() {
        ProgressDialog progress;
        progress=ProgressDialog.show(this,"Logging In","",true);
        User=ed1.getText().toString();
        password= ed2.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+User+"/"+password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Boolean success  ;
                    JSONObject jsonObject = new JSONObject(response);
                    success=jsonObject.getBoolean("success");
                    progress.dismiss();
                    if(success==false)
                    {
                        Toast.makeText(Login_page.this, "User Credential is wrong please try again", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String ngoId= User;

                        Intent intent=new Intent(Login_page.this,window2aftersplash.class);
                        intent.putExtra("ngo_id",ngoId);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }


        }
        );

        requestQueue.add(stringRequest);
    }
//    @Override
//    public void onBackPressed() {
//
//        return;
//    }
}