package com.boomer.alphaassault.graphics.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/27/2015.
 */
public class SightCamera extends OrthographicCamera {
    Location LOCATION;
    int SIGHT;

    public SightCamera(int _sight) {
        SIGHT = _sight;
        LOCATION = new Location(0,0);
    }

    public SightCamera(int _sight,float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        SIGHT = _sight;
    }

    public void setSight(int _sight){
        SIGHT = _sight;
        this.update();
    }

    public void setLocation(Location _location){
        LOCATION = _location;
        this.translate(LOCATION.x,LOCATION.y);
        this.update();
    }

    public void setLocation(float _x,float _y){
        LOCATION = new Location(_x,_y);
        this.translate(LOCATION.x,LOCATION.y);
        this.update();
    }




}
