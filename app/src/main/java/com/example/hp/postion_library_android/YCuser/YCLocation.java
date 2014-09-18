package com.example.hp.postion_library_android.YCuser;

import android.graphics.PointF;

/**
 * Created by HP on 2014/9/17.
 */
public class YCLocation {

    private PointF point;
    private double accuracy;

    public void YCLocation()
    {
        point = new PointF(0,0);
        accuracy = 0;
    }
    public void YCLocation(PointF p,double d)
    {
        point = p;
        accuracy =d;
    }
    public PointF getCoordinate()
    {
        return point;
    }
    public double getAccuracy()
    {
        return accuracy;
    }
}
