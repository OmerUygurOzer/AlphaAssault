package com.boomer.alphaassault.utilities;

import sun.util.resources.cldr.byn.CalendarData_byn_ER;

/**
 * Created by Omer on 11/24/2015.
 */
public class Location {
    public float x;
    public float y;


   public Location(float _x,float _y){
        x = _x;
        y= _y;

    }

    public Location(Location _location){
        x = _location.x;
        y = _location.y;
    }

    public static double getDistance(Location _a,Location _b){
        return  Math.sqrt(((_a.x-_b.x)*(_a.x-_b.x)) + ((_a.y-_b.y)*(_a.y-_b.y)) );
    }

    public boolean equals(Location _location){
        return x==_location.x && y==_location.y;
    }

    public static double getDistance(int _x1,int _y1,int _x2,int _y2){
        return Math.sqrt(((_x1-_x2)*(_x1-_x2)) + ((_y1-_y2)*(_y1-_y2)) );
    }

    public static double getAngle(Location _a, Location _b){
        double angle = Math.toDegrees(Math.atan2(_a.x - _b.x,_a.y- _b.y));
        if(angle<360){angle+=360;}
        return angle;
    }

}
