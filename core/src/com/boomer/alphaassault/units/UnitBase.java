package com.boomer.alphaassault.units;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;
import com.boomer.alphaassault.utilities.Ticker;


import java.awt.*;
import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class UnitBase implements Ticker, Renderable {

    //GRAPHICS DETAILS
    public static final Texture unitTexture = new Texture(Gdx.files.internal("character.png"));

    //TYPE DETAILS
    public static final int UNIT_TYPE_ASSAULT = 0;
    public static final int UNIT_TYPE_ENGINEER = 1;
    public static final int UNIT_TYPE_COMMANDO = 2;
    public static final int UNIT_TYPE_MEDIC = 3;

    //TEAM DETAILS
    public static final int TEAM_RED = 0;
    public static final int TEAM_BLUE = 1;

    //MECHANIC DETAILS
    protected int RADIUS;
    public double FACING_ANGLE;
    protected Location LOCATION;


    //TYPE PROPERTIES
    //COMMON
    public static final int UNIT_RADIUS = 6;
    //ASSAULT TROOPER
    public static final int ASSAULT_HP = 12;
    public static final int ASSAULT_RANGE = 8;
    public static final int ASSAULT_SIGHT = 8;
    public static final int ASSAULT_FIRE_SPEED = 4;
    public static final int ASSAULT_DAMAGE = 4;
    public static final int ASSAULT_MOVEMENT_SPEED = 8;
    public static final int ASSAULT_AMMO_S1 = 2;
    public static final int ASSAULT_AMMO_S2 = 0;
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
    public int HP;
    private int TYPE;
    public int TEAM;
    public int AMMO_S1;
    public int AMMO_S2;
    public int DAMAGE;
    private int RANGE;
    protected int MOVEMENT_SPEED;
    private int FIRE_SPEED;
    private int SIGHT;
    private boolean FIRE_READY;
    protected boolean INVISIBLE;

    //TIMERS
    long FIRE_TIMER;

    /*
    * TEST!!!!
    * */

    protected Sprite character;

    UnitBase(int _TYPE, int _TEAM, Location _location){


        Random random = new Random();
        FACING_ANGLE = random.nextInt((359 - 0) + 1) + 0;
        TYPE = _TYPE;
        TEAM = _TEAM;
        RADIUS = UNIT_RADIUS;
        LOCATION = _location;
        FIRE_READY = true;
        INVISIBLE = false;

        character = new Sprite(unitTexture);//TEST
        //character.scale(2);
        character.setSize(40,40);
        character.setCenter(LOCATION.x,LOCATION.y);


        switch(TYPE){
            case UNIT_TYPE_ASSAULT:
                HP = ASSAULT_HP;
                RANGE = ASSAULT_RANGE;
                SIGHT = ASSAULT_SIGHT;
                FIRE_SPEED = ASSAULT_FIRE_SPEED;
                DAMAGE = ASSAULT_DAMAGE;
                MOVEMENT_SPEED = ASSAULT_MOVEMENT_SPEED;
                AMMO_S1 = ASSAULT_AMMO_S1;
                AMMO_S2 = ASSAULT_AMMO_S2;
                break;
            case UNIT_TYPE_COMMANDO:
                HP = COMMANDO_HP;
                RANGE = COMMANDO_RANGE;
                SIGHT = COMMANDO_SIGHT;
                FIRE_SPEED = COMMANDO_FIRE_SPEED;
                DAMAGE = COMMANDO_DAMAGE;
                MOVEMENT_SPEED = COMMANDO_MOVEMENT_SPEED;
                AMMO_S1 = COMMANDO_AMMO_S1;
                AMMO_S2 = COMMANDO_AMMO_S2;
                break;
            case UNIT_TYPE_ENGINEER:
                HP = ENGINEER_HP;
                RANGE = ENGINEER_RANGE;
                SIGHT = ENGINEER_SIGHT;
                FIRE_SPEED = ENGINEER_FIRE_SPEED;
                DAMAGE = ENGINEER_DAMAGE;
                MOVEMENT_SPEED = ENGINEER_MOVEMENT_SPEED;
                AMMO_S1 = ENGINEER_AMMO_S1;
                AMMO_S2 = ENGINEER_AMMO_S2;
                break;
            case UNIT_TYPE_MEDIC:
                HP =  MEDIC_HP;
                RANGE = MEDIC_RANGE;
                SIGHT = MEDIC_SIGHT;
                FIRE_SPEED = MEDIC_FIRE_SPEED;
                DAMAGE = MEDIC_DAMAGE;
                MOVEMENT_SPEED = MEDIC_MOVEMENT_SPEED;
                AMMO_S1 = MEDIC_AMMO_S1;
                AMMO_S2 = MEDIC_AMMO_S2;
                break;
            default:
                break;

        }



    }

    public void fire(){
        if(FIRE_READY){
            FIRE_TIMER = System.currentTimeMillis();
            FIRE_READY = false;
            //FIRE
        }

    }



    public abstract void resupply();

    public abstract void move(double angle);

    @Override
    public void tick() {
            if (!FIRE_READY) {
                if(FIRE_TIMER + (1000/FIRE_SPEED) < System.currentTimeMillis()) {
                    FIRE_READY = true;
                }
            }

    }

    public Location getLocation(){
        return LOCATION;
    }

    public int getRadius(){
        return RADIUS;
    }

    public boolean isInvisible(){
        return INVISIBLE;
    }




}
