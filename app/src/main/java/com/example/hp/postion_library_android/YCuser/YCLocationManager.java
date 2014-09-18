package com.example.hp.postion_library_android.YCuser;

import android.content.Context;
import android.util.Log;

/**
 * Created by HP on 2014/9/17.
 */
public class YCLocationManager {

    private static final String TAG = "YCLocationManager";
    private static boolean debuginfo = true;

    private Context context;
    protected static YCLocationManager ycLocationManager = null;

    private YCLocationListener mylistener;

    protected YCLocationManager(Context context)
    {
        this.context=context;
    }
    public void bund(YCConsumer consumer) {

    }

    public void unbund(YCConsumer consumer) {

    }

    public void setlocationlistener(YCLocationListener listener) {
        mylistener = listener;
    }

    public void startpostion() {

    }

    public void stoppostion() {

    }
    private static void debuglog(String tag,String info)
    {
        if(debuginfo)
        {
            Log.d(TAG,info);
        }
    }
    public static YCLocationManager getInstanceForApplication(Context context)
    {
        if (ycLocationManager == null)
        {
            ycLocationManager = new YCLocationManager(context);
        }
        return ycLocationManager;
    }


}
