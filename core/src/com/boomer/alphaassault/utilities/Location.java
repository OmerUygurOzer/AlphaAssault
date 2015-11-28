package com.boomer.alphaassault.utilities;

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

    public static float getAngle(Location _a, Location _b){

        return 0f;
    }

}
