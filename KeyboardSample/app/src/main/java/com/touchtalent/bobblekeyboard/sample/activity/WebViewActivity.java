package com.touchtalent.bobblekeyboard.sample.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.touchtalent.bobblekeyboard.sample.R;

/**
 * This activity demonstrates the use of WebView in your project.
 * This is important because of a android bug which causes certain IME APIs to malfunction (resulting in ANR)
 * when a IME and WebView work in the same process.
 * Refer the
 */
public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ((WebView) findViewById(R.id.webView)).loadUrl("https://www.google.com");
        WebViewClient client = new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

        };
        ((WebView) findViewById(R.id.webView)).setWebViewClient(client);
    }
}