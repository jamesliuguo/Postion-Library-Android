package com.example.hp.postion_library_android.YCuser;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

/**
 * Created by HP on 2014/9/18.
 */
public interface YCConsumer {
    public void onServiceConnect();
    public Context getApplicationContext();
    public void bundService(ServiceConnection connection);
    public void unBundService(Intent intent,ServiceConnection connection);
}
