package com.example.hp.postion_library_android.YCserve;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.ibeacon.RangeNotifier;

import java.util.Collection;

public class YCLocationService extends Service implements IBeaconConsumer {
    private static final String TAG = "YCLocationService";
    private boolean debuginfo = true;

    private IBeaconManager iBeaconManager=IBeaconManager.getInstanceForApplication(this);


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onIBeaconServiceConnect() {
        iBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {

            }
        });
    }
    private void debuglog(String tag,String info)
    {
        if(debuginfo)
            Log.d(TAG, info);
    }
}
