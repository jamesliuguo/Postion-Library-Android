package com.example.hp.postion_library_android.YCuser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import com.example.hp.postion_library_android.YCserve.YCLocationService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by HP on 2014/9/17.
 * 定位管理类，将用户的Activity或者Service和 定位的YCLocationService绑定或者解绑
 * 开启或者关闭定位坐标更新
 */
public class YCLocationManager
{

    private static final String TAG = "YCLocationManager";     //本类的标志
    private static boolean debuginfo = true;                      //是否输出此类的调试信息

    private Context context;                                        //本类的上下文
    protected static YCLocationManager ycLocationManager = null;    //本类对象的实例引用（单例方法，整个项只有一个类的实例对象）

    private YCLocationListener mylistener;                          //回调接口，用来向使用者传递坐标（YCLocation）

    private Messenger YCLocationServiceMessager = null;         //定义和YCLocationService信息交互的信差
    private Map<YCConsumer, ConsumerInfo> consumers = new HashMap<YCConsumer, ConsumerInfo>();    //存储使用定位服务的YCConsumer实例（Activity、Service）

    //类的初始化
    protected YCLocationManager(Context context)
    {
        this.context = context;
    }

    //绑定YCConsumer和YCLocationService
    public void bind(YCConsumer consumer)
    {
        synchronized (consumer)
        {
            if (consumers.keySet().contains(consumer))
            {
                debuglog(TAG, "this YCConsumer is already bound");
            } else
            {
                debuglog(TAG, "this YCConsumer is not bound . binding" + consumer);
                consumers.put(consumer, new ConsumerInfo());
                Intent intent = new Intent(consumer.getApplicationContext(), YCLocationService.class);
                consumer.bundService(intent, YCLocationServiceConnection, Context.BIND_AUTO_CREATE);
                debuglog(TAG, "YCConsumer that has been bound is now:" + consumers.size());
            }
        }
    }

    //取消绑定
    public void unbind(YCConsumer consumer)
    {
        synchronized (consumer)
        {
            if (consumers.keySet().contains(consumer))
            {
                debuglog(TAG, "this YCConsumer is unbinding:" + consumer);
                consumer.unBundService(YCLocationServiceConnection);
                consumers.remove(consumer);
                if (consumers.size() == 0)
                {
                    YCLocationServiceMessager = null;
                }
            } else
            {
                debuglog(TAG, "this consumer is not bound to :" + consumer);
            }
        }
    }

    //判断是否绑定
    public boolean isBound(YCConsumer consumer)
    {
        synchronized (consumer)
        {
            return consumers.keySet().contains(consumer) && (YCLocationServiceMessager != null);
        }
    }

    public void setlocationlistener(YCLocationListener listener)
    {
        mylistener = listener;
    }

    //开始更新定位坐标
    public void startpostion()
    {

    }

    //停止定位坐标更新
    public void stoppostion()
    {

    }

    //输出调试信息
    private static void debuglog(String tag, String info)
    {
        if (debuginfo)
        {
            Log.d(TAG, info);
        }
    }

    //返回此类实例的引用
    public static YCLocationManager getInstanceForApplication(Context context)
    {
        if (ycLocationManager == null)
        {
            ycLocationManager = new YCLocationManager(context);
        }
        return ycLocationManager;
    }

    //代表YCLocationServiceConnection将YCConsumer与YCLocationService绑定的链接
    private ServiceConnection YCLocationServiceConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            debuglog(TAG, "we have a connection to the YCLocationService now");
            YCLocationServiceMessager = new Messenger(iBinder);  //建立和YCLocationService的绑定后，初始化与其信息交互的信差

            //将consumers中当前consumer链接状态置真
            synchronized (consumers)
            {
                Iterator<YCConsumer> consumerIterator = consumers.keySet().iterator();
                while (consumerIterator.hasNext())
                {
                    YCConsumer consumer = consumerIterator.next();
                    Boolean alreadyConnected = consumers.get(consumer).isConnected;
                    if (!alreadyConnected)
                    {
                        consumer.onServiceConnect();
                        ConsumerInfo consumerInfo = consumers.get(consumer);
                        consumerInfo.isConnected = true;
                        consumers.put(consumer, consumerInfo);
                    }
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {

        }
    };

    //YCConsumer是否绑定
    private class ConsumerInfo
    {
        public boolean isConnected = false;
    }
}

