package com.rodavid20.withsplashscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class camplist extends AppCompatActivity {

//    public static String url = "https://soorveer-api.herokuapp.com/api/camp/get/";

    public static String url = "https://blood-camp-api-production.up.railway.app/api/camp/get/";

//    public static String url = "https://dull-teal-dolphin-coat.cyclic.app/api/camp/get/";

    String ngo_id;
    public JSONObject jsonObject1;
    RecyclerView recyclerView;
    Adaptar adaptar;
    String name="";
    RequestQueue queue;
    List<modelclass> camps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camplist);
        handleSSLHandshake();
        Intent intent = getIntent();
        ngo_id=intent.getStringExtra("ngo_id");
        Toast.makeText(this, "ngo_id:- "+ngo_id, Toast.LENGTH_SHORT).show();
        setTitle("CAMP LIST");
        camps= new ArrayList<>();
        getData();




    }

    public void  getData() {
        ProgressDialog progress;
        progress = ProgressDialog.show(this,"please wait","",true);
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, url+ngo_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    length = jsonArray.length();
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject campObject = jsonArray.getJSONObject(i);

                        modelclass camp = new modelclass();
                        name= campObject.getString("name").toString();
                        camp.setTextview1(campObject.getString("name").toString());
                        camp.setTextview2(campObject.getString("address").toString());
                        camp.setTextview3(campObject.getString("mobile").toString());
                        camps.add(camp);
                    }
//
                    Log.i("data :", String.valueOf(camps.size()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progress.dismiss();
                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adaptar = new Adaptar( getApplicationContext(),camps);
                recyclerView.setAdapter(adaptar);

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

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            @SuppressLint("CustomX509TrustManager") TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }

    }

}

