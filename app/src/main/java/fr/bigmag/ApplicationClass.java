package fr.bigmag;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bealder.sdk.manager.BealderParameters;
import com.bealder.sdk.manager.BealderSDK;
import com.bealder.sdk.tool.StringRequest;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import fr.bigmag.adapter.MessageAdapter;
import fr.bigmag.pojo.Message;


public class ApplicationClass extends Application implements BootstrapNotifier {

    private RegionBootstrap regionBootstrap;
    private BealderSDK bealderSDK;
    private String PARSE_APPLICATION_ID = "NjRKDL4oGWYYmSyLgXc5pUhE7hvNhPf9CQ8hUKmW";
    private String PARSE_CLIENT_KEY = "8T0iEnKlHwNbpOFbSfzy8kEzfh4b3l5NZDRz7Oti";

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);

        // Initialise Bealder
        bealderSDK = new BealderSDK(this);

        // Show debug in logcat
        //BealderParameters.setDebugMod();

        // Set Icon - require -
        BealderParameters.setLogo(R.mipmap.ic_launcher);

        // If Token Push, send it, any time
        //BealderParameters.setTokenPush(TOKEN_PUSH);

        // Define uuid scan
        //BealderParameters.setUuid("F7826DA6-4FA2-4E98-8024-BC5B71E0893E");

        // Set region to bootstrap
        regionBootstrap = new RegionBootstrap(this, bealderSDK.getRegion());

        bealderSDK.run(this);

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                String deviceToken = (String) ParseInstallation.getCurrentInstallation().get("deviceToken");
                Log.e("WTF", "TOKEN:"+deviceToken);
                bealderSDK.sendPush(deviceToken);
            }
        });
    }

    @Override
    public void didEnterRegion(Region region) {
        bealderSDK.enterRegion(region);
    }

    @Override
    public void didExitRegion(Region region) {
        bealderSDK.exitRegion(region);
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        // Do nothing
    }

}