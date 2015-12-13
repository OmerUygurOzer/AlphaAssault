package com.boomer.alphaassault.graphics.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 11/27/2015.
 */
public class SightCamera extends OrthographicCamera {
    private Location location;
    private int sight;


    private static final int SIGHT_SCALE = 40;


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
        location = new Location(0,0);


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
