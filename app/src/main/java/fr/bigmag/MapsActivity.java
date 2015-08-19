package fr.bigmag;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bealder.sdk.tool.StringRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MapsActivity extends BaseActivity {
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<MarkerOptions> markers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_maps);

        setUpMapIfNeeded();
        downloadLocations();
    }

    protected void downloadLocations() {
        final View loader = findViewById(R.id.loader);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest messagesRequest = new StringRequest(Request.Method.GET, "https://api.bealder.com/v2/region", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.pin);

                try {

                    JSONArray mcdonalds = new JSONArray(response);

                    for (int i = 0; i < mcdonalds.length(); i++) {
                        JSONObject mcdonald = null;
                        mcdonald = mcdonalds.getJSONObject(i);

                        MarkerOptions marker = new MarkerOptions();
                        marker.position(new LatLng(mcdonald.getDouble("latitude"), mcdonald.getDouble("longitude")));
                        marker.title(mcdonald.optString("name", ""));
                        marker.icon(icon);

                        markers.add(marker);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setUpMapWithMarkers();
                loader.setVisibility(View.GONE);
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

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * Initial zoom over Clermont Ferrand
     */
    private void setUpMap() {
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(45.7831, 3.0824), 6);
        mMap.moveCamera(cu);

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMapWithMarkers() {
        if(mMap == null) {
            return;
        }

        for(MarkerOptions marker : markers) {
            mMap.addMarker(marker);
        }

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (MarkerOptions m : markers) {
            builder.include(m.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int padding = 40; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        mMap.animateCamera(cu);
        mMap.setMyLocationEnabled(true);
    }
}
