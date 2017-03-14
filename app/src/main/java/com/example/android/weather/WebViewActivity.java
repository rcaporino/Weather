package com.example.android.weather;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by rob on 3/13/17.
 */

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.weather.com");

    }
}
