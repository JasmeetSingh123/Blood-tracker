package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class afterclickdonarlist extends AppCompatActivity {

    TextView name,email,addhar,mobile,blood_group,times_donated,address;
    Button update,delete;
    String camp_id;
    ImageView next,prev;
    int index=0;
    RequestQueue queue;
    int length=0;
    String id="";


//    private static String url ="https://soorveer-api.herokuapp.com/api/donar/get/";
//    private static String url1 ="https://soorveer-api.herokuapp.com/api/donar";

    private static String url ="https://blood-camp-api-production.up.railway.app/api/donar/get/";
    private static String url1 ="https://blood-camp-api-production.up.railway.app/api/donar";

//    private static String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar/get/";
//    private static String url1 ="https://dull-teal-dolphin-coat.cyclic.app/api/donar";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterclickdonarlist);

        Intent intent1 = getIntent();
        camp_id=intent1.getStringExtra("camp_id");



        name=findViewById(R.id.donor_name);
        email=findViewById(R.id.donor_email);
        addhar=findViewById(R.id.donor_addhar);
        mobile=findViewById(R.id.donor_mobile);
        blood_group=findViewById(R.id.donor_blood_group);
        times_donated=findViewById(R.id.donor_times_donated);
        address=findViewById(R.id.donor_address);
        next=findViewById(R.id.next);
        prev=findViewById(R.id.previous);
        delete=findViewById(R.id.delete);
        update=findViewById(R.id.update);

        update.setOnClickListener(view -> {

            Intent intent=new Intent(afterclickdonarlist.this,afterclickingdonorupdate.class);
            intent.putExtra("index",index);
            intent.putExtra("camp_id",camp_id);
            startActivity(intent);
        });

        getData();


        prev.setOnClickListener(View->{
            index = index-1;
            if(index>=0)
            {
                getData();
            }
            else
            {
                Toast.makeText(this, "This is the first page", Toast.LENGTH_SHORT).show();
            }


        });

        next.setOnClickListener(View->{
            index=index+1;
            if(index>length-1)
            {
                index=length-1;
                Toast.makeText(this, "This is the Last Page", Toast.LENGTH_SHORT).show();
            }
            else
            {
                getData();
            }

        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url1+"/"+id,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(index==length-1)
                                {
                                    index= index-1;
                                }
                                getData();
                                // Display the first 500 characters of the response string.
                                Toast.makeText(afterclickdonarlist.this, "response", Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(afterclickdonarlist.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                requestQueue.add(stringRequest);
            }
        });


    }




    public void getData()
    {
        ProgressDialog progress;
        progress=ProgressDialog.show(this,"Loading","",true);
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+camp_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject jsonObject = new JSONObject(response);
//                    if(jsonObject == null) {
//                        Log.i("reeponse", null);
//                        progress.dismiss();
//                    }

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    length = jsonArray.length();
                    if(length ==0)
                    {
                        Toast.makeText(afterclickdonarlist.this, "No sample is added in this camp", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        progress.dismiss();
                    }
                    JSONObject jsonObject1 = jsonArray.getJSONObject(index);
                    progress.dismiss();
                    id=jsonObject1.getString("_id");
                    name.setText(jsonObject1.getString("donarname"));
                    email.setText(jsonObject1.getString("email"));
                    addhar.setText(jsonObject1.getString("aadhar"));
                    mobile.setText(jsonObject1.getString("mobile"));
                    blood_group.setText(jsonObject1.getString("bloodGroup"));
                    times_donated.setText(jsonObject1.getString("times"));
                    address.setText(jsonObject1.getString("address"));
                    //camp.setText(jsonObject1.getString("orgId"));


                    Log.i("data :", String.valueOf(jsonObject1));
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
        queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}