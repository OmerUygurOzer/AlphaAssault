package com.boomer.alphaassault.gameworld.gamelogic;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 12/17/2015.
 */
public abstract class Entity {
    protected Vector2 center;
    protected boolean removed;
    protected float radius;
    protected long referenceId;
    protected int depth;


    protected Entity(Vector2 _center,int _depth){
        center = _center;
        removed = false;
        depth = _depth;
        referenceId = System.nanoTime();
    }

    public void setCenter(float _x,float _y){
        center.x  = _x;
        center.y = _y;
    }
    public boolean isRemoved(){return removed;}
    public Vector2 getCenter(){return center;}
    public float getRadius(){return radius;}

    public static boolean doesCollide(Entity _first, Entity _second){
        double radiant = _first.getRadius() + _second.getRadius();
        double distance = GameMath.getDistance(_first.getCenter(),_second.getCenter());
        if(radiant >= distance){return true;}
        return false;
    }

    public long getReferenceId(){return referenceId;}
    public int getDepth(){return depth;}
}
