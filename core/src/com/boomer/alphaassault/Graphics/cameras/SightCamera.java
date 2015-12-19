package com.boomer.alphaassault.graphics.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 11/27/2015.
 */
public class SightCamera extends OrthographicCamera {
    private Vector2 location;
    private int sight;


    private static final int SIGHT_SCALE = 40;


    public SightCamera(){
        super();
    }

    public SightCamera(int _sight) {
        super();
        sight = _sight;
        location = new Vector2(0,0);

    }

    public SightCamera(int _sight,float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        sight = _sight;
        location = new Vector2(0,0);


    }

    public void setSight(int _sight){
        sight = _sight;

        this.update();
    }

    public void setPosition(Vector2 _location){
       location = _location;
        this.translate(location.x, location.y);
        this.update();
    }

    public void setPosition(int _x,int _y){
        location = new Vector2(_x,_y);
        this.position.set(_x,_y,this.position.z);
        this.update();

    }

    public int getSight(){return sight;}



}
