package com.nifty.cloud.mb.core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.android.gms.gcm.GcmReceiver;

/**
 * Custom GcmReceiver for Google Cloud Messaging
 */
public class NCMBGcmReceiver extends GcmReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences recentPushIdPref = context.getSharedPreferences("ncmbPushId", Context.MODE_PRIVATE);
        String recentPushId = recentPushIdPref.getString("recentPushId", "");
        String currentPushId = intent.getStringExtra("com.nifty.PushId");
        if (!recentPushId.equals(currentPushId)) {
            SharedPreferences.Editor editor = recentPushIdPref.edit();
            editor.putString("recentPushId", currentPushId);
            editor.commit();

            super.onReceive(context, intent);
        }

    }
}
