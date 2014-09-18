package com.example.hp.postion_library_android.YCstruct;

/**
 * Created by HP on 2014/9/17.
 */
public class YCFingerPrintDataStruct
{
    public int id;
    public String map = new String();
    public int major;
    public int pointid;
    public int x;
    public int y;

    public YCFingerPrintDataStruct(int id, String map, int major, int pointid, int x, int y) {
        this.id = id;
        this.map = map;
        this.major = major;
        this.pointid = pointid;
        this.x = x;
        this.y = y;
    }
}
