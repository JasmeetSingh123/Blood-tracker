package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class getQrgenerator extends AppCompatActivity {

    //    public static String url ="https://soorveer-api.herokuapp.com/api/donar/ejs/";
//    public static String url1= "https://soorveer-api.herokuapp.com/api/donar/admin/";

    public static String url ="https://blood-camp-api-production.up.railway.app/api/donar/ejs/";
    public static String url1= "https://blood-camp-api-production.up.railway.app/api/donar/admin/";

//    public static String url ="https://dull-teal-dolphin-coat.cyclic.app/api/donar/ejs/";
//    public static String url1= "https://dull-teal-dolphin-coat.cyclic.app/api/donar/admin/";

    WebView mWebview;

    String donar_id = "";
    Button get;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_qrgenerator);

        mWebview = findViewById(R.id.qrWeb);
        get=findViewById(R.id.details);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            donar_id = bundle.getString("donar_id");
            //location= bundle.getString("location");
        }

        get.setOnClickListener(view -> {
            Intent intent=new Intent(getQrgenerator.this,getDetails.class);
            intent.putExtra("id",donar_id);
            startActivity(intent);
        });






        generateQR(url+donar_id);
    }

    public void generateQR (String donar_id) {
        String URL = "https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=" + donar_id;


        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getQrgenerator.this, "Thanks for generating", Toast.LENGTH_SHORT).show();
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


@Override
public void onBackPressed() {
    super.onBackPressed();
    Intent intent=new Intent(getQrgenerator.this,window1aftersplash.class);
    startActivity(intent);
}

}