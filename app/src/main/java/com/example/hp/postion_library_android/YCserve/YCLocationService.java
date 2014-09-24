package com.example.hp.postion_library_android.YCserve;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.hp.postion_library_android.YCuser.YCLocation;
import com.example.hp.postion_library_android.YCuser.YCLocationListener;
import com.example.hp.postion_library_android.YCuser.YCLocationManager;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.ibeacon.RangeNotifier;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.logging.Handler;

/*定位服务类,此服务会通过第三方库，并结合定位算法，得到定位坐标，使用者需要在配置文件中说明此服务*/
public class YCLocationService extends Service implements IBeaconConsumer {
    private static final String TAG = "YCLocationService";
    private boolean debuginfo = true;
    private YCLocation ycLocation_current = new YCLocation();
    private IBinder iBinder = new YcBinder();
    public static final int MSG_START_LOCATION = 1;
    public static final int MSG_STOP_LOCATION = 2;
    public YCLocation ycLocation = new YCLocation();
    private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
    private YCLocationManager ycLocationManager = YCLocationManager.getInstanceForApplication(this);
    /*final Messenger mMessenger = new Messenger(new IncomingHandler(this));

    static  class IncomingHandler extends android.os.Handler
    {
        private final WeakReference<YCLocationService> mservice;
        IncomingHandler(YCLocationService service)
        {
            mservice = new WeakReference<YCLocationService>(service);
        }
        @Override
        public void handleMessage(android.os.Message msg)
        {
            YCLocationService service = mservice.get();
            if(service!=null)
            {
                switch (msg.what)
                {
                    case MSG_START_LOCATION:
                        break;
                    case MSG_STOP_LOCATION:
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        }
    }*/
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      //  throw new UnsupportedOperationException("Not yet implemented");
       // return mMessenger.getBinder();
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        iBeaconManager.bind(this);
        debuglog(TAG, "YCloactionService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        debuglog(TAG, "YCloactionService onDestroy");
        iBeaconManager.unBind(this);
    }

    @Override
    public void onIBeaconServiceConnect() {
        iBeaconManager.setRangeNotifier(new RangeNotifier()
        {
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region)
            {
                debuglog(TAG, "iBeaconManager didRangeBeacons");
                if(ycLocationManager!=null)
                {
                    debuglog(TAG, "send to user");
                    ycLocation.setAccuracy(2.5);
                    ycLocation.setPoint(65,54);
                    ycLocationManager.called(ycLocation);
                }
            }
        });
        try {
            iBeaconManager.startRangingBeaconsInRegion(new Region("mymonitor", "d26d197e-4a1c-44ae-b504-dd7768870564", null, null));
        } catch (RemoteException e) {
            debuglog(TAG, "iBeaconManager startrangingBeaconsInRegion fail");
            e.printStackTrace();
        }
    }

    private void debuglog(String tag, String info) {
        if (debuginfo)
            Log.d(TAG, info);
    }

    private class YcBinder extends Binder implements YCAddBinder
    {

        @Override
        public YCLocation getLocation()
        {
            return ycLocation_current;
        }

        @Override
        public boolean stopLocation()
        {
            return false;
        }

        @Override
        public boolean startLocation()
        {
            return false;
        }
    }

}