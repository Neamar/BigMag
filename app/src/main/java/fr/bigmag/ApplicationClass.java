package fr.bigmag;

import android.app.Application;
import com.bealder.sdk.manager.BealderParameters;
import com.bealder.sdk.manager.BealderSDK;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

public class ApplicationClass extends Application implements BootstrapNotifier {

    private RegionBootstrap regionBootstrap;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialise Bealder - require -
        BealderSDK bealderSDK = BealderSDK.getInstance(this);

        // Show debug in logcat
        //BealderParameters.setDebugMod();

        // Set Icon - require -
        BealderParameters.setLogo(R.mipmap.ic_launcher);

        // If Token Push, send it, any time
        //BealderParameters.setTokenPush(TOKEN_PUSH);

        // Set region to bootstrap - require -
        regionBootstrap = new RegionBootstrap(this, bealderSDK.getRegion());

        // - require -
        BealderSDK.run(this);

    }

    @Override
    public void didEnterRegion(Region region) {
        BealderSDK.enterRegion(region);
    }

    @Override
    public void didExitRegion(Region region) {
        BealderSDK.exitRegion(region);
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        // Do nothing
    }
}