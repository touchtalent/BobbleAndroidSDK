package com.touchtalent.bobblekeyboard.sample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.touchtalent.bobbleime.sdk.BobbleEnablerActivity;
import com.touchtalent.bobbleime.sdk.BobbleIMESDK;
import com.touchtalent.bobblekeyboard.sample.R;

/**
 * This typically is a activity where promotional content about the Keyboard would be mentioned.
 * This activity hosts a button which will start the keyboard enabling flow.
 */
public class ActivationActivity extends BobbleEnablerActivity implements View.OnClickListener {
    private static final String TAG = ActivationActivity.class.getSimpleName();

    Button step1;
    Button step2;
    EditText tryOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bobble);
        step1 = findViewById(R.id.step1);
        step2 = findViewById(R.id.step2);
        tryOut = findViewById(R.id.tryOut);
        step1.setOnClickListener(this);
        step2.setOnClickListener(this);
        //Set initial UI state based on Keyboard status.
        setStatus(getStatus());
    }

    public void setStatus(BobbleIMESDK.IMEInstallStatus status) {
        switch (status) {
            case NONE:
                changeState(step1, true);
                changeState(step2, false);
                tryOut.setVisibility(View.GONE);
                break;
            case ENABLED:
                changeState(step1, false);
                changeState(step2, true);
                tryOut.setVisibility(View.GONE);
                break;
            case SELECTED:
                step1.setVisibility(View.GONE);
                step2.setVisibility(View.GONE);
                tryOut.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void changeState(View view, boolean active) {
        view.setVisibility(View.VISIBLE);
        view.setFocusable(active);
        view.setClickable(active);
        float alpha = active ? 1f : 0.2f;
        view.setAlpha(alpha);
    }

    /**
     * When the user successfully completes the keyboard enabling flow, the activity back-stack will have System's Language setting as back item.
     * Hence, it is important to bring the source activity to front to maintain correct sequence of activities.
     */
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /**
     * Start the activation flow on user click, all further process will be handled by the SDK.
     */
    @Override
    public void onClick(View v) {
        startActivationFlow();
    }

    @Override
    protected void onStatusChange(@NonNull BobbleIMESDK.IMEInstallStatus imeInstallStatus) {
        setStatus(imeInstallStatus);
        Log.d(TAG, "onStatusChange: " + imeInstallStatus.name());
    }

    /**
     * The user has successfully setup and landed on this page. Taking him/her back to parent activity.
     */
    @Override
    protected void onUserSetup() {
        Log.d(TAG, "onUserSetup()");
        onBackPressed();
    }
}
