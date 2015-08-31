package fr.bigmag.pojo;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    public int id;
    public String name;
    public String promotion;

    public Message(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.name = json.getString("title");
            this.promotion = json.getString("text");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return "https://api.bealder.com/v2/app/history/" + this.id;
    }
}