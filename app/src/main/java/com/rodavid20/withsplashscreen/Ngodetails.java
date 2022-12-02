package com.rodavid20.withsplashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import java.lang.ref.Reference;

public class Ngodetails extends AppCompatActivity {

    // Variables
    TextInputLayout nregistrationid,ndor,ntype,nurl,naddress,nname,nstate,ndistrict,npan,ncsr,password;
    Button Ngosubmitbtn;
    RequestQueue queue;

//    String url ="https://soorveer-api.herokuapp.com/api/ngo";

    String url ="https://blood-camp-api-production.up.railway.app/api/ngo";

//    String url ="https://dull-teal-dolphin-coat.cyclic.app/api/ngo";

    FirebaseDatabase rootNode2;
    DatabaseReference reference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetails);
        nregistrationid=findViewById(R.id.RegId);
        ndor=findViewById(R.id.Regdate);
        ntype=findViewById(R.id.ngotype);
        nurl=findViewById(R.id.url);
        naddress=findViewById(R.id.ngoaddress);
        nname=findViewById(R.id.ngoname);
        nstate=findViewById(R.id.Ngostate);
        ndistrict=findViewById(R.id.district);
        npan=findViewById(R.id.pan);
        ncsr=findViewById(R.id.csr);
        password=findViewById(R.id.ngpassword);
//        nnitiaayog=findViewById(R.id.nitiaayog);
//        nannualreport=findViewById(R.id.report);
        Ngosubmitbtn=findViewById(R.id.nsubmit);

        Ngosubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode2=FirebaseDatabase.getInstance();
                reference2=rootNode2.getReference("ngo");

                // Getting all values

                String RegID=nregistrationid.getEditText().getText().toString();
                String Name=nname.getEditText().getText().toString();
                String Date_of_Registration=ndor.getEditText().getText().toString();
                String Type=ntype.getEditText().getText().toString();
                String Ngo_URL=nurl.getEditText().getText().toString();
                String Address=naddress.getEditText().getText().toString();
                String State=nstate.getEditText().getText().toString();
                String District=ndistrict.getEditText().getText().toString();
                String Pan_number=npan.getEditText().getText().toString();
                String CSR_Number=ncsr.getEditText().getText().toString();
                String password1=password.getEditText().getText().toString();



                queue = Volley.newRequestQueue(getApplicationContext());
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("Unique_Registration_ID",RegID);
                    jsonObject.put("NGO_Name",Name);
                    jsonObject.put("NGO_Type",Type);
                    jsonObject.put("NGO_Url",Ngo_URL);
                    jsonObject.put("NGO_Address",Address);
                    jsonObject.put("NGO_State",State);
                    jsonObject.put("NGO_District",District);
                    jsonObject.put("NGO_PAN_Number",Pan_number);
                    jsonObject.put("CSR_Number",CSR_Number);
                    jsonObject.put("password",password1);

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Ngodetails.this, "NGO added successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Response : ",response.toString());
                        Intent intent1=new Intent(Ngodetails.this,window1aftersplash.class);
                        startActivity(intent1);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Ngodetails.this, "Enter full details", Toast.LENGTH_SHORT).show();
                        Log.d("Error : ",error.toString());
                    }
                });
                queue.add(jsonObjectRequest);








            }
        });

    }
}