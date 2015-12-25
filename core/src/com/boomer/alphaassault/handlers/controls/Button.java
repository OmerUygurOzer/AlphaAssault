package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.graphics.elements.BSprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Omer on 12/24/2015.
 */
public class Button {
    protected Vector2 center;

    protected int width;
    protected int height;

    protected static final int STATE_INIT = 0;


    public Button(float _x, float _y, int _width, int _height){
        center = new Vector2(_x,_y);
        width = _width;
        height = _height;
    }

    public Vector2 getCenter(){return center;}
    public int getWidth(){return width;}
    public int getHeight(){return height;}


}
