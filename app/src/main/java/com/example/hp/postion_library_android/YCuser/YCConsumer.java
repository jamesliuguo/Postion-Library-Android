package com.example.hp.postion_library_android.YCuser;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by HP on 2014/9/18.
 */
public interface YCConsumer
{
    /*当YCLocationService 运行后，就会在YCLocationManager中调用*/
    public void onServiceConnect();

    //    在YCLocationmanager中调用得到Activity或者Service的Context。重载函数
    public Context getApplicationContext();

//    在YCLoactionmanager中调用，来 取消YCconsumer和YCLocationService的绑定。重载函数

    public boolean bindService(Intent intent, ServiceConnection connection, int mode);

    //    在YCLoactionmanager中调用，来绑定YCconsumer和YCLocationService。重载函数
    public void unbindService(ServiceConnection connection);

}

