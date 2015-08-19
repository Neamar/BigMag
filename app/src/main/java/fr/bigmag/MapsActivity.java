package fr.bigmag;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bealder.sdk.tool.StringRequest;

/**
 * Created by neamar on 8/18/15.
 */
public class MapsActivity extends BaseActivity {
    protected void downloadLocations() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest messagesRequest = new StringRequest(Request.Method.GET, "https://api.bealder.com/v2/region", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("WTF", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        // Send the request
        queue.add(messagesRequest);
    }
}
