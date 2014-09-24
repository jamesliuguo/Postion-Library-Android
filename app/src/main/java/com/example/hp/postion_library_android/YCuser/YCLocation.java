package com.example.hp.postion_library_android.YCuser;

import android.graphics.PointF;

/**
 * Created by HP on 2014/9/17.
 */
public class YCLocation
{

    private PointF point;
    private double accuracy;

    public void YCLocation()
    {
        point = new PointF(0, 0);
        accuracy = 0;
    }

    public void YCLocation(PointF p, double d)
    {
        point = p;
        accuracy = d;
    }

    public void setPoint(float x,float y)
    {
        point = new PointF(x,y);
    }
    public void setPoint(PointF point)
    {
        this.point = point;
    }
    public void setAccuracy(double accuracy)
    {
        this.accuracy =accuracy;
    }
    public PointF getPoint()
    {
        return point;
    }
    public float getX()
    {
        return point.x;
    }
    public float getY()
    {
        return point.y;
    }
    public double getAccuracy()
    {
        return accuracy;
    }
}
