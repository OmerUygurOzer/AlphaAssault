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

    Location(Location _location){
        x = _location.x;
        y = _location.y;
    }

}
