package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class createcamp extends AppCompatActivity {
    Button bt1;
    String ngoID;
    TextInputLayout name,address,coordinator,mobile,email;
    RequestQueue queue;

//    String url="https://soorveer-api.herokuapp.com/api/camp";

    String url="https://blood-camp-api-production.up.railway.app/api/camp";

//    String url="https://dull-teal-dolphin-coat.cyclic.app/api/camp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcamp);
        Intent intent = getIntent();
        ngoID = intent.getStringExtra("ngo_id");
        Toast.makeText(this, "ngo_detail"+ngoID, Toast.LENGTH_SHORT).show();
        bt1=findViewById(R.id.nsubmit);
        name=findViewById(R.id.donarname);
        email=findViewById(R.id.donaremail);
        address=findViewById(R.id.donaraddress);
        coordinator=findViewById(R.id.coordinatorname);
        mobile=findViewById(R.id.Mobile_number);
        bt1.setOnClickListener(View->{
            Log.i("inf ", name.getEditText().toString());
//            name.getEditText().toString().equals("") || address.getEditText().toString().equals("") ||
//                    coordinator.getEditText().toString().equals("") || mobile.getEditText().toString().equals("")
//                    || email.getEditText().toString().equals("")
            postData();


        });
    }

    public void postData(){
        queue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",name.getEditText().getText().toString());
            jsonObject.put("address",address.getEditText().getText().toString());
            jsonObject.put("coordinator",coordinator.getEditText().getText().toString());
            jsonObject.put("mobile",mobile.getEditText().getText().toString());
            jsonObject.put("email",email.getEditText().getText().toString());
            jsonObject.put("ngo_id",ngoID);


        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                    Toast.makeText(createcamp.this, "Camp added successfully", Toast.LENGTH_SHORT).show();
                    Log.d("Response : ",response.toString());

                Toast.makeText(getApplicationContext(), "Camp creates successfully", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(),window2aftersplash.class);
                intent1.putExtra("ngo_id",ngoID);
                startActivity(intent1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                    Toast.makeText(createcamp.this, "Enter the details", Toast.LENGTH_SHORT).show();

                Log.d("Error : ",error.toString());
            }
        });
        queue.add(jsonObjectRequest);


    }

}