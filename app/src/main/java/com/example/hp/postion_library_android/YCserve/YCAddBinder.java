package com.example.hp.postion_library_android.YCserve;

import com.example.hp.postion_library_android.YCuser.YCLocation;

/**
 * Created by HP on 2014/9/23.
 */
public interface YCAddBinder
{
    public YCLocation getLocation();
    public boolean stopLocation();
    public boolean startLocation();
}
