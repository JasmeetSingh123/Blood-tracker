package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

public class donardetails extends AppCompatActivity {

    TextInputLayout dname,demail,daadhar,dmobile,dblood,dfreq,daddress,dorgid;
    Button submitbtn;
    RequestQueue queue;
    String camp_id;
    String location;

//    String url ="https://soorveer-api.herokuapp.com/api/donar";

    String url ="https://blood-camp-api-production.up.railway.app/api/donar";

//    String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar";

    String recievedDonar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donardetails);

        Intent intent1 = getIntent();
        camp_id=intent1.getStringExtra("camp_id");
        location = intent1.getStringExtra("location");
        dname=findViewById(R.id.name);
        demail=findViewById(R.id.email);
        daadhar=findViewById(R.id.aadhar);
        dmobile=findViewById(R.id.mobile);
        dblood=findViewById(R.id.bloodgp);
        dfreq=findViewById(R.id.times);
        daddress=findViewById(R.id.address);
        submitbtn=findViewById(R.id.donarsubmit);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // get all the values

                String name=dname.getEditText().getText().toString();
                String email=demail.getEditText().getText().toString();
                String aadhar=daadhar.getEditText().getText().toString();
                String mobile=dmobile.getEditText().getText().toString();
                String blood=dblood.getEditText().getText().toString();
                String freq=dfreq.getEditText().getText().toString();
                String address=daddress.getEditText().getText().toString();

                queue = Volley.newRequestQueue(getApplicationContext());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("camp_id",camp_id);
                    jsonObject.put("donarname",name);
                    jsonObject.put("email",email);
                    jsonObject.put("aadhar",aadhar);
                    jsonObject.put("mobile",mobile);
                    jsonObject.put("bloodGroup",blood);
                    jsonObject.put("times",freq);
                    jsonObject.put("address",address);

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            recievedDonar= response.getString("_id");
                            Log.i("id:-  ", recievedDonar);
                            Toast.makeText(donardetails.this, "Donar added successfully", Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(donardetails.this,qrgenerator.class);
                        intent.putExtra("donar_id",recievedDonar);
                        intent.putExtra("location",location);
                        startActivity(intent);
                        Log.d("Response : ",response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(donardetails.this, "All details are required", Toast.LENGTH_SHORT).show();
                        Log.d("Error : ",error.toString());
                    }
                });
                queue.add(jsonObjectRequest);

            }
        });

    }
}