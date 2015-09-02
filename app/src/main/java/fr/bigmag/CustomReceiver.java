package fr.bigmag;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bealder.sdk.tool.Notification;
import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by neamar on 9/2/15.
 */
public class CustomReceiver extends ParsePushBroadcastReceiver {
    @Override
    protected void onPushReceive(Context context, Intent intent) {
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            JSONObject notificationData = new JSONObject(pushData.getString("alert"));
            notificationData = notificationData.getJSONObject("data");

            Notification.send(notificationData.optString("title", "Big mag"), notificationData.optString("alert", ""), notificationData.optString("url", ""), context);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("WTF", "Crahsed!" + e.toString());
        }
    }
}
