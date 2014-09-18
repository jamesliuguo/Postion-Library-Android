package com.example.hp.postion_library_android;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.example.hp.postion_library_android.YCdb.YCDB;
import com.radiusnetworks.ibeacon.Region;

import java.util.Collection;

public class MyActivity extends Activity  implements IBeaconConsumer{

    private final String TAG = "MyActivity";
    private boolean debuginfo = true;
    private IBeaconManager iBeaconManager=IBeaconManager.getInstanceForApplication(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        iBeaconManager.bind(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void debuglog(String tag,String info)
    {
        if(debuginfo)
        {
            Log.d(tag,info);
        }
    }

    @Override
    public void onIBeaconServiceConnect()
    {
        iBeaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
                debuglog(TAG,"range");
            }
        });
        try {
            iBeaconManager.startRangingBeaconsInRegion(new Region("mymonitor","d26d197e-4a1c-44ae-b504-dd7768870564",null,null));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
