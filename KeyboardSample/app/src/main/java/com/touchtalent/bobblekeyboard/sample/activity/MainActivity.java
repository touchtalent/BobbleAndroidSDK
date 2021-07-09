package com.touchtalent.bobblekeyboard.sample.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.touchtalent.bobblekeyboard.sample.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.launch).setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.webView).setOnClickListener(v->{
            startActivity(new Intent(this, WebViewActivity.class));
        });
    }
}