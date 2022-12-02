package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class afterclickingdonorupdate extends AppCompatActivity {

//    private static String url ="https://soorveer-api.herokuapp.com/api/donar/get/";
//    private static String url1 ="https://soorveer-api.herokuapp.com/api/donar";

    private static String url ="https://blood-camp-api-production.up.railway.app/api/donar/get/";
    private static String url1 ="https://blood-camp-api-production.up.railway.app/api/donar";

//    private static String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar/get/";
//    private static String url1 ="https://dull-teal-dolphin-coat.cyclic.app/api/donar";

    TextInputEditText uname,uemail,uaddhar,umobile,ublood_gp,uunits_donated,uaddress;
    Button confirm;
    String camp_id;
    String id="";
    int index=0;
    RequestQueue queue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterclickingdonorupdate);
        Intent intent1 = getIntent();
        camp_id=intent1.getStringExtra("camp_id");

        uname=findViewById(R.id.uname);
        uemail=findViewById(R.id.uemail);
        uaddhar=findViewById(R.id.uaddhar);
        umobile=findViewById(R.id.umobile);
        ublood_gp=findViewById(R.id.ublood_gp);
        uunits_donated=findViewById(R.id.uunits_donated);
        uaddress=findViewById(R.id.uaddress);
        confirm=findViewById(R.id.donorconfirm);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {

            index = bundle.getInt("index");
            camp_id=bundle.getString("camp_id");
        }
        Log.i("camp", camp_id);


        getData();


        confirm.setOnClickListener(View -> {
            queue = Volley.newRequestQueue(getApplicationContext());
            JSONObject jsonObject = new JSONObject();
            try {
//                camp_id=jsonObject.getString("camp_id");
                jsonObject.put("donarname", uname.getText().toString());
                jsonObject.put("email", uemail.getText().toString());
                jsonObject.put("aadhar", uaddhar.getText().toString());
                jsonObject.put("mobile", umobile.getText().toString());
                jsonObject.put("bloodGroup", ublood_gp.getText().toString());
                jsonObject.put("times",uunits_donated.getText().toString());
                jsonObject.put("address", uaddress.getText().toString());



            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url1+"/"+id, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(afterclickingdonorupdate.this, "Donar updated", Toast.LENGTH_SHORT).show();
                    Log.d("Response : ", response.toString());
                    Intent intent = new Intent(afterclickingdonorupdate.this,afterclickdonarlist.class);
                    intent.putExtra("camp_id",camp_id);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error : ", error.toString());
                }
            });
            queue.add(jsonObjectRequest);
        });
    }
    public void getData()
    {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+camp_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    JSONObject jsonObject1 = jsonArray.getJSONObject(index);

                    id = jsonObject1.getString("_id");
                    uname.setText(jsonObject1.getString("donarname"));
                    uemail.setText(jsonObject1.getString("email"));
                    uaddhar.setText(jsonObject1.getString("aadhar"));
                    umobile.setText(jsonObject1.getString("mobile"));
                    ublood_gp.setText(jsonObject1.getString("bloodGroup"));
                    uunits_donated.setText(jsonObject1.getString("times"));
                    uaddress.setText(jsonObject1.getString("address"));



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