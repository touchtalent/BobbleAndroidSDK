package com.touchtalent.bobblekeyboard.sample.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.touchtalent.bobblekeyboard.sample.R;

/**
 * This the the activity that can be deep linked to the Brand icon present on keyboard.
 */
public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
    }
}