package com.example.hp.postion_library_android.YCdb;

/**
 * Created by chenlei on 2014/8/6.
 *
 * 指纹数据库操作类
 */

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.File;

public class YCDB
{
    private final String TAG ="YCDB";              // 类标志
    private boolean debuginfo = true;      //是否输出本类调试信息

    private  SQLiteDatabase database_fingerprint = null ;

    public YCDB()
    {
        debuglog(TAG,"YCDB constructor");
    /*//  debuglog(TAG,android.os.Environment.getExternalStorageDirectory().getAbsolutePath());
        File dir=new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"sample.db");
        if(dir.exists())
        {
            debuglog(TAG,"true");
        }
        else
            debuglog(TAG,"false");*/
    }
    //    设置本地指纹数据源
    public boolean setdatabase(int id)
     {
         return true;
     }
    //    设置本地指纹数据源
    public boolean setdatabase(String path)
    {
        return true;
    }
    //    设置本地指纹数据源
    public boolean setdatabase(File file)
    {
        return true;
    }
    //返回本地数据源的版本
    public int getdatabaseversion()
    {
        int version=0;
        /*  add code*/
        return version;
    }

//    返回本地数据源中的数据
   /* public List<YCFingerPrintDataStruct> getfingerprintlist()
    {

    }*/

    private void debuglog(String tag,String info)
    {
        if(debuginfo)
        {
            Log.d(tag,info);
        }
    }
}
