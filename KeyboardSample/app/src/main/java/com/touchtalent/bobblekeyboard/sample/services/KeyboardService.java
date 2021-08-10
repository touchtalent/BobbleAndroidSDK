package com.touchtalent.bobblekeyboard.sample.services;

import android.content.Intent;

import com.touchtalent.bobbleime.services.BobbleIME;
import com.touchtalent.bobblekeyboard.sample.activity.DeepLinkActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class KeyboardService extends BobbleIME {

    /**
     * Handle user's click on Brand icon on keyboard
     */
    @Override
    public void onQuickAccessIconTap() {
        Intent intent = new Intent(this, DeepLinkActivity.class);
        //This flag is necessary since the activity is being opened from a Service context.
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
