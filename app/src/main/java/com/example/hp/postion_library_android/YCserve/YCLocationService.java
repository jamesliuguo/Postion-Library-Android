package com.example.hp.postion_library_android.YCserve;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.ibeacon.RangeNotifier;

import java.util.Collection;
/*定位服务类,此服务会通过第三方库，并结合定位算法，得到定位坐标，使用者需要在配置文件中说明此服务*/
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
        iBeaconManager.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iBeaconManager.unBind(this);
    }

    @Override
    public void onIBeaconServiceConnect() {
        iBeaconManager.setRangeNotifier(new RangeNotifier(){
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region)
            {
                debuglog(TAG,"iBeaconManager didRangeBeacons");
            }
        });
        try {
            iBeaconManager.startRangingBeaconsInRegion(new Region("mymonitor","d26d197e-4a1c-44ae-b504-dd7768870564",null,null));
        } catch (RemoteException e) {
            debuglog(TAG,"iBeaconManager startrangingBeaconsInRegion fail");
            e.printStackTrace();
        }
    }
    private void debuglog(String tag,String info)
    {
        if(debuginfo)
            Log.d(TAG, info);
    }
}
