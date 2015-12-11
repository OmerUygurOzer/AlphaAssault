package com.boomer.alphaassault.utilities;

/**
 * Created by Omer on 12/8/2015.
 */
public enum Rotation {
    RIGHT,
    LEFT,
    UP,
    DOWN,
    UP_RIGHT,
    UP_LEFT,
    DOWN_RIGHT,
    DOWN_LEFT;
    public static Rotation getRotation(double _angle){
        if(_angle <= 100 && _angle >80){return RIGHT;}
        if(_angle <=280 && _angle > 260){return LEFT;}
        if((_angle >350 && _angle  <=360)||(_angle < 10 && _angle >= 0)){return UP;}
        if(_angle <= 190 && _angle > 170){return DOWN;}
        if(_angle <= 80 && _angle >=10) {return UP_RIGHT;}
        if(_angle <=170 && _angle >=100){return DOWN_RIGHT;}
        if(_angle <=260 && _angle >=190){return DOWN_LEFT;}
        if(_angle <=350 && _angle >=280){return UP_LEFT;}
        return null;
    }
}
