package com.rodavid20.withsplashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class getDetails extends AppCompatActivity {
    WebView mwebview;
    private static String url="https://blood-camp-api-production.up.railway.app/api/donar/ejs/";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);
        mwebview=findViewById(R.id.qrWeb);
        Bundle b=getIntent().getExtras();
        if (b!=null){
            id=getIntent().getStringExtra("id").toString();
        }
        mwebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getDetails.this, "Thanks for generating", Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mwebview.loadUrl(url+id);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getDetails.this,window1aftersplash.class);
        startActivity(intent);
    }


}