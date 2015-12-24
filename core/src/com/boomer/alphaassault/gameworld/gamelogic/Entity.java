package com.boomer.alphaassault.gameworld.gamelogic;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.utilities.GameMath;

/**
 * Created by Omer on 12/17/2015.
 */
public abstract class Entity implements Updateable{

    protected Vector2 center;
    protected boolean removed;
    protected float radius;
    protected short referenceId;
    protected int depth;
    protected GameWorld world;
    protected boolean isSolid;
    protected float size;

    protected Entity(Vector2 _center,int _depth,GameWorld _world){
        center = _center;
        removed = false;
        depth = _depth;
        world = _world;
        referenceId = GameSystem.obtainReference();
    }

    public void setCenter(float _x,float _y){
        center.x  = _x;
        center.y = _y;
    }

    public final void markRemoved(){removed = true;}
    public final void markNew(){removed = false;}

    public final boolean isRemoved(){return removed;}
    public final Vector2 getCenter(){return center;}
    public final float getRadius(){return radius;}
    public final short getReferenceId(){return referenceId;}
    public final int getDepth(){return depth;}

    public abstract void uponCollision(Entity _entity);
    public abstract void receiveHit(int _hit,Unit _source);

    public static boolean doesCollide(Entity _first, Entity _second){
        double radiant = _first.getRadius() + _second.getRadius();
        double distance = GameMath.getDistance(_first.getCenter(),_second.getCenter());
        if(radiant >= distance){return true;}
        return false;
    }
}
