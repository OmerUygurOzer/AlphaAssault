package com.boomer.alphaassault.utilities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 12/17/2015.
 */
public class GameMath {

    public static double getDistance(Vector2 _first, Vector2 _second){
        return java.lang.Math.sqrt(((_first.x-_second.x)*(_first.x-_second.x)) + ((_first.y-_second.y)*(_first.y-_second.y)) );
    }

    public static double getDistance(float _x1,float _y1,float _x2,float _y2){
        return java.lang.Math.sqrt(((_x1-_x2)*(_x1-_x2)) + ((_y1-_y2)*(_y1-_y2)) );
    }

    public static double getAngle(float _x1,float _y1,float _x2,float _y2){
        double angle = java.lang.Math.toDegrees(java.lang.Math.atan2(_x1 - _x2,_y1- _y2));
        if(angle<0){angle+=360;}
        return angle;
    }

}
