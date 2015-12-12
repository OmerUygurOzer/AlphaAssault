package com.boomer.alphaassault.graphics.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/27/2015.
 */
public class SightCamera extends OrthographicCamera {
    private Location location;
    private int sight;

    public SightCamera(){
        super();
    }

    public SightCamera(int _sight) {
        super();
        sight = _sight;
        location = new Location(0,0);
    }

    public SightCamera(int _sight,float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        sight = _sight;
    }

    public void setSight(int _sight){
        sight = _sight;
        this.update();
    }

    public void setPosition(Location _location){
        location = _location;
        this.translate(location.x, location.y);
        this.update();
    }

    public void setPosition(int _x,int _y){
        location = new Location(_x,_y);
        this.position.set(_x,_y,this.position.z);
        this.update();
    }

    public int getSight(){return sight;}




}
