package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class aftercampid extends AppCompatActivity {
    String ngo_id,camp_id;
    TextView cname,caddress,coordinator,cmobile,cemail;

//    public static String url="https://soorveer-api.herokuapp.com/api/camp/";

//    public static String url="https://dull-teal-dolphin-coat.cyclic.app/api/camp/";

    public static String url="https://blood-camp-api-production.up.railway.app/api/camp/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aftercampid);
        handleSSLHandshake();
        Intent intent = getIntent();
        ngo_id=intent.getStringExtra("ngo_id");
        camp_id=intent.getStringExtra("camp_id");
        cname=findViewById(R.id.camp_name);
        caddress=findViewById(R.id.camp_address);
        coordinator=findViewById(R.id.camp_coordinator_name);
        cmobile=findViewById(R.id.camp_mobile);
        cemail=findViewById(R.id.camp_email);
        Log.i("inf", camp_id);
        getData();

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

                    JSONObject jsonObject1 = new JSONObject(response);
                    progress.dismiss();
                    cname.setText(jsonObject1.getString("name"));
                    caddress.setText(jsonObject1.getString("address"));
                    coordinator.setText(jsonObject1.getString("coordinator"));
                    cmobile.setText(jsonObject1.getString("mobile"));
                    cemail.setText(jsonObject1.getString("email"));

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