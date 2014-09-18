package com.example.hp.postion_library_android.YCuser;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by HP on 2014/9/18.
 */
public interface YCConsumer {
    /*当YCLocationService 经过经过算法定位后得到定位坐标后，就会在YCLocationManager中调用*/
    public void onServiceConnect();

    /*在YCLocationmanager中调用得到Activity或者Service的Context*/
    public Context getApplicationContext();

    /*在YCLoactionmanager中调用，来绑定YCconsumer和YCLocationService*/
    public void bundService(ServiceConnection connection);

    /*在YCLoactionmanager中调用，来 取消YCconsumer和YCLocationService的绑定
    * */
    public void unBundService(Intent intent,ServiceConnection connection);
}
