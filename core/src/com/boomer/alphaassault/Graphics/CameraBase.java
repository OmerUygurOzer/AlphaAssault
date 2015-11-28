package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.math.Matrix4;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/26/2015.
 */
public abstract class CameraBase {
    Location CENTER;
    Matrix4 CAMERA_MATRIX;
    int RADIUS;

    protected CameraBase(Location _LOCATION,Matrix4 _MATRIX){
        CENTER = new Location(_LOCATION);
        CAMERA_MATRIX = _MATRIX;
    }

    protected CameraBase(Location _LOCATION,int _RADIUS){
        CENTER = new Location(_LOCATION);
        RADIUS = _RADIUS;
    }
}
