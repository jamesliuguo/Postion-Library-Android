package com.example.hp.postion_library_android;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hp.postion_library_android.YCserve.YCLocationService;
import com.example.hp.postion_library_android.YCuser.YCConsumer;
import com.example.hp.postion_library_android.YCuser.YCLocation;
import com.example.hp.postion_library_android.YCuser.YCLocationListener;
import com.example.hp.postion_library_android.YCuser.YCLocationManager;
import com.radiusnetworks.ibeacon.IBeaconConsumer;

//开发测试使用的临时Activity
public class MyActivity extends Activity implements YCConsumer{

    private final String TAG = "MyActivity";
    private boolean debuginfo = true;
    private Intent intent=null;
    private YCLocationManager ycLocationManager =YCLocationManager.getInstanceForApplication(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ycLocationManager.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ycLocationManager.unbind(this);
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
    public void onServiceConnect()
    {
          debuglog(TAG,"location is running");
        ycLocationManager.setlocationlistener(new YCLocationListener()
        {
            @Override
            public void YCGetLocation(int i)
            {
                //debuglog(TAG,"get location:x="+location.getCoordinate().x+" y="+location.getCoordinate().y);
                debuglog(TAG,"YCGetLocation location :"+i);            }
        });

    }
}
