package com.boomer.alphaassault.gameworld.units;


import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.Updateable;


import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class Unit implements Updateable,Renderable{

    //CONSTANTS
    protected static final float UNIT_SIZE = 40;


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
    protected int movementSpeed;
    protected int sight;

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


/*
        switch(type){
            case UNIT_TYPE_ASSAULT:
                HP = ASSAULT_HP;
                range = ASSAULT_RANGE;
                sight = ASSAULT_SIGHT;
                firingSpeed = ASSAULT_FIRE_SPEED;
                damage = ASSAULT_DAMAGE;
                movementSpeed = ASSAULT_MOVEMENT_SPEED;
                AMMO_S1 = ASSAULT_AMMO_S1;
                AMMO_S2 = ASSAULT_AMMO_S2;
                break;
            case UNIT_TYPE_COMMANDO:
                HP = COMMANDO_HP;
                range = COMMANDO_RANGE;
                sight = COMMANDO_SIGHT;
                firingSpeed = COMMANDO_FIRE_SPEED;
                damage = COMMANDO_DAMAGE;
                movementSpeed = COMMANDO_MOVEMENT_SPEED;
                AMMO_S1 = COMMANDO_AMMO_S1;
                AMMO_S2 = COMMANDO_AMMO_S2;
                break;
            case UNIT_TYPE_ENGINEER:
                HP = ENGINEER_HP;
                range = ENGINEER_RANGE;
                sight = ENGINEER_SIGHT;
                firingSpeed = ENGINEER_FIRE_SPEED;
                damage = ENGINEER_DAMAGE;
                movementSpeed = ENGINEER_MOVEMENT_SPEED;
                AMMO_S1 = ENGINEER_AMMO_S1;
                AMMO_S2 = ENGINEER_AMMO_S2;
                break;
            case UNIT_TYPE_MEDIC:
                HP =  MEDIC_HP;
                range = MEDIC_RANGE;
                sight = MEDIC_SIGHT;
                firingSpeed = MEDIC_FIRE_SPEED;
                damage = MEDIC_DAMAGE;
                movementSpeed = MEDIC_MOVEMENT_SPEED;
                AMMO_S1 = MEDIC_AMMO_S1;
                AMMO_S2 = MEDIC_AMMO_S2;
                break;
            default:
                break;

        }

*/
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
}
