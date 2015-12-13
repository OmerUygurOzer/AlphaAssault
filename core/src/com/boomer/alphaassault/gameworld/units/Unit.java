package com.boomer.alphaassault.gameworld.units;


import com.boomer.alphaassault.gameworld.gamelogic.buffs.Buff;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.Updateable;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class Unit implements Updateable,Renderable{

    //CONSTANTS
    protected static final float UNIT_SIZE = 40;
    public static final int MAX_SPEED = 10;

    //MECHANICAL/GRAPHICAL DETAILS
    protected int radius;
    protected double facingAngle;
    protected Location location;
    private long referenceId;
    protected int viewType;
    protected BAnimation bAnimation;

    //TYPE PROPERTIES
    //COMMON
    public static final int UNIT_RADIUS = 6;

    //ENGINEER
    public static final int ENGINEER_HP = 6;
    public static final int ENGINEER_RANGE = 6;
    public static final int ENGINEER_SIGHT = 10;
    public static final int ENGINEER_FIRE_SPEED = 3;
    public static final int ENGINEER_DAMAGE = 3;
    public static final int ENGINEER_MOVEMENT_SPEED = 8;
    public static final int ENGINEER_AMMO_S1 = 3;
    public static final int ENGINEER_AMMO_S2 = 3;
    //COMMANDO
    public static final int COMMANDO_HP = 10;
    public static final int COMMANDO_RANGE = 12;
    public static final int COMMANDO_SIGHT = 10;
    public static final int COMMANDO_FIRE_SPEED = 1;
    public static final int COMMANDO_DAMAGE = 12;
    public static final int COMMANDO_MOVEMENT_SPEED = 8;
    public static final int COMMANDO_AMMO_S1 = 1;
    public static final int COMMANDO_AMMO_S2 = 0;
    //MEDIC
    public static final int MEDIC_HP = 8;
    public static final int MEDIC_RANGE = 6;
    public static final int MEDIC_SIGHT = 8;
    public static final int MEDIC_FIRE_SPEED = 3;
    public static final int MEDIC_DAMAGE = 3;
    public static final int MEDIC_MOVEMENT_SPEED = 12;
    public static final int MEDIC_AMMO_S1 = 2;
    public static final int MEDIC_AMMO_S2 = 1;

    //IN-GAME DETAILS
    protected int HP;
    private int team;
    protected int damage;
    protected int range;
    protected int baseMovementSpeed;
    protected int adjustedMovementSpeed;
    protected int sight;
    protected List<Buff> buffs;

    protected boolean invisibility;

    //TIMERS
    long FIRE_TIMER;



    protected Unit(int _team, Location _location){


        Random RANDOM = new Random();
        facingAngle = RANDOM.nextInt((359 - 0) + 1) + 0;
        team = _team;
        radius = UNIT_RADIUS;
        location = _location;
        //readyToFire = true;
        invisibility = false;
        adjustedMovementSpeed = 0;

        buffs = new ArrayList<Buff>();


    }


    public Location getLocation(){return location;}
    public void setLocation(float _x, float _y){
        location.x = _x;
        location.y = _y;
    }
    public int getRadius(){return radius;}
    public boolean isInvisible(){return invisibility;}
    public boolean isAlive() {return (HP>0);}
    public int getHP(){return HP;}
    public int getTeam(){return team;}

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {}
    //RENDERABLE
    @Override
    public void addToRenderState() {RenderStateManager.addElement(viewType, referenceId, RenderState.DEPTH_SURFACE, bAnimation);}
    @Override
    public long getReferenceID() {return referenceId;}
    @Override
    public void setViewType(int _viewType) {viewType = _viewType;}
    @Override
    public void setReferenceID(long _referenceId) {
    referenceId = _referenceId;
    }
    public abstract void fire();
    public abstract void resupply();
    public abstract void move(float _deltaTime,float _x,float _y,double _angle);

    //BUFFS
    public void addBuff(Buff _buff){buffs.add(_buff);}
    public void removeBuff(Buff _buff){buffs.remove(_buff);}

    //MOVEMENT ADJUSTMENTS
    public void adjustMovementSpeed(int _adjustment){adjustedMovementSpeed += _adjustment;}
    public int getBaseMovementSpeed(){return baseMovementSpeed;}
    public int getMovementSpeed(){return baseMovementSpeed + adjustedMovementSpeed;}

}
