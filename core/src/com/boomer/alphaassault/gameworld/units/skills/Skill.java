package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.graphics.Texture;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 12/1/2015.
 */
public abstract class Skill {

    public static final int TARGET_TYPE_SELF = 0;
    public static final int TARGET_TYPE_UNIT = 1;
    public static final int TARGET_TYPE_POINT = 2;

    protected Texture icon;

    protected int key;
    protected int targetType;
    protected Unit user;

    //VARIABLES
    protected long cooldown;
    protected long timer;
    protected Supply supply;
    protected String supplyName;
    protected boolean ready;

    public class Supply{
        public int COUNT_MAX;
        public int count;
        public Texture icon;
        public String name;

    }

    protected Skill(int _key){
        key = _key;
        ready = true;
    }

    //METHODS
    public abstract void resupply();
    public abstract void use();
    public abstract void use(Unit _unit);
    public abstract void use(Location _target);
    public abstract void update();
    public boolean isReady(){return ready;}
    public int getKey(){return key;}
    public int getTargetType(){return targetType;}
    public void setUser(Unit _unit){user = _unit;}
    public Texture getIcon(){return icon;}
    public Supply getSupply(){return supply;}
}
