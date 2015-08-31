package fr.bigmag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bealder.sdk.AdvertActivity;
import com.bealder.sdk.tool.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import fr.bigmag.adapter.MessageAdapter;
import fr.bigmag.pojo.Message;

/**
 * Created by neamar on 8/19/15.
 */
public class MessagesActivity extends BaseActivity {
    private ProgressDialog progress = null;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_messages);

        progress = new ProgressDialog(this);
        progress.setTitle("Chargement");
        progress.setMessage("Nous récupérons vos promotions.");
        progress.show();

        initializeListView();
        downloadLocations();
    }

    private void initializeListView() {
        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Message message = (Message) parent.getItemAtPosition(position);
                Intent intent = new Intent(MessagesActivity.this, AdvertActivity.class);
                intent.putExtra("url", message.getUrl());
                startActivity(intent);
            }
        });
    }

    protected void downloadLocations() {
        final View loader = findViewById(R.id.loader);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest messagesRequest = new StringRequest(Request.Method.GET, "https://api.bealder.com/v2/app/history", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Message> venues = new ArrayList<>();

                try {
                    JSONArray jsonVenues = new JSONArray(response);
                    for (int i = 0; i < jsonVenues.length(); i++) {
                        venues.add(new Message(jsonVenues.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MessageAdapter adapter = new MessageAdapter(MessagesActivity.this, venues);
                listView.setAdapter(adapter);
                listView.setEmptyView(findViewById(android.R.id.empty));

                progress.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                listView.setEmptyView(findViewById(android.R.id.empty));
                Toast.makeText(MessagesActivity.this, "Erreur lors de la récupération des promotions :(", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        // Send the request
        queue.add(messagesRequest);
    }
}
