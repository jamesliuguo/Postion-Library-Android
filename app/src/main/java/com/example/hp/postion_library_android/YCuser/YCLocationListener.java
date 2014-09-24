package com.example.hp.postion_library_android.YCuser;

import com.example.hp.postion_library_android.YCuser.YCLocation;

/**
 * Created by HP on 2014/9/17.
 */
public interface YCLocationListener
{
    /*回调，当得到定位点坐标后*/
    public void YCGetLocation(YCLocation ycLocation);
}
