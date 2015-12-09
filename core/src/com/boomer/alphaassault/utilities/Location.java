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

    public Location(){
        x = 0f;
        y = 0f;
    }

    public boolean equals(Location _location){
        return x==_location.x && y==_location.y;
    }

    public static double getDistance(int _x1,int _y1,int _x2,int _y2){
        return Math.sqrt(((_x1-_x2)*(_x1-_x2)) + ((_y1-_y2)*(_y1-_y2)) );
    }

    public static double getDistance(float _x1,float _y1,float _x2,float _y2){
        return Math.sqrt(((_x1-_x2)*(_x1-_x2)) + ((_y1-_y2)*(_y1-_y2)) );
    }

    public static double getAngle(int _x1,int _y1,int _x2,int _y2){
        double angle = Math.toDegrees(Math.atan2(_x1 - _x2,_y1- _y2));
        if(angle<0){angle+=360;}
        return angle;
    }

    public static double getAngle(float _x1,float _y1,float _x2,float _y2){
        double angle = Math.toDegrees(Math.atan2(_x1 - _x2,_y1- _y2));
        if(angle<0){angle+=360;}
        return angle;
    }

}
