package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class qrgenerator extends AppCompatActivity {

//    public static String url ="https://soorveer-api.herokuapp.com/api/donar/ejs/";
//    public static String url1= "https://soorveer-api.herokuapp.com/api/donar/admin/";

    public static String url ="https://blood-camp-api-production.up.railway.app/api/donar/ejs/";
    public static String url1= "https://blood-camp-api-production.up.railway.app/api/donar/admin/";

//    public static String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar/ejs/";
//    public static String url1= "https://dull-teal-dolphin-coat.cyclic.app/api/donar/admin/";

    WebView mWebview;

    String donar_id = "";
    String location;
    RequestQueue queue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        mWebview = findViewById(R.id.qrWebView);
// SaveBtn = findViewById(R.id.qrSaveBtn);

// mWebview = findViewById(R.id.qrW)


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            donar_id = bundle.getString("donar_id");
            location= bundle.getString("location");
        }

// SaveBtn.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View view) {
//
// }
// });


        setLocation();

        generateQR(url+donar_id);
    }

    public void generateQR (String donar_id) {
        String URL = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=" + donar_id;


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(qrgenerator.this, "Thanks for generating", Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.loadUrl(URL);
    }

    public void setLocation(){
        queue = Volley.newRequestQueue(getApplicationContext());
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("location",location);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url1+donar_id, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error : ",error.toString());
            }
        });
        queue.add(jsonObjectRequest);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(qrgenerator.this,donardetails.class);
        startActivity(intent);
    }
}