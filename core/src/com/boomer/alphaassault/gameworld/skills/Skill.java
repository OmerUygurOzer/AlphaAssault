package com.boomer.alphaassault.gameworld.skills;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;

/**
 * Created by Omer on 12/1/2015.
 */
public abstract class Skill {

    public static final int TARGET_TYPE_SELF = 0;
    public static final int TARGET_TYPE_UNIT = 1;
    public static final int TARGET_TYPE_POINT = 2;
    public static final int TARGET_TYPE_ANGLE  = 3;

    protected TextureRegion icon;

    protected int key;
    protected int targetType;
    protected Unit user;

    //VARIABLES
    protected long cooldown;
    protected long timer;
    protected Supply supply;
    protected String supplyName;
    protected boolean ready;

    //REFERENCE TO THE GAMEWORLD
    protected GameWorld world;

    public class Supply{
        public int COUNT_MAX;
        public int count;
        public TextureRegion icon;
        public String name;

    }

    protected Skill(Unit _user,int _key){
        key = _key;
        ready = true;
        user = _user;
    }

    //METHODS

    public final void setWorld(GameWorld _world){world = _world;}


    public abstract void resupply();
    public abstract void use();
    public abstract void use(Unit _unit);
    public abstract void use(Vector2 _target);
    public abstract void use(float _angle);
    public abstract void update();
    public final boolean isReady(){return ready;}
    public final int getKey(){return key;}
    public final int getTargetType(){return targetType;}
    public final TextureRegion getIcon(){return icon;}
    public final Supply getSupply(){return supply;}

}
