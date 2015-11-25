package com.boomer.alphaassault.units;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class AssaultTrooper extends UnitBase implements AssaultTrooperSkillSet {

    private boolean FLASHBANG_READY;
    private long FLASHBANG_TIMER;
    private int FLASHBANG_FIRE_SPEED;

    private boolean RUN_READY;
    private boolean RUN_ACTIVE;
    private static final int RUN_COOLDOWN = 10;
    private static final int RUN_DURATION = 4;
    private long RUN_DURATION_TIMER;
    private long RUN_COOLDOWN_TIMER;

    private static final int RUN_BOOST = 2;



    public AssaultTrooper(int _TEAM, Location _location) {
        super(UNIT_TYPE_ASSAULT, _TEAM, _location);
        FLASHBANG_READY = true;
        FLASHBANG_FIRE_SPEED = 10;
        RUN_ACTIVE = false;
        RUN_READY = true;

    }


    @Override
    public void flashbang() {
        if(AMMO_S1 >0){
            if(FLASHBANG_READY) {
                AMMO_S1 -=1;
                FLASHBANG_TIMER = System.currentTimeMillis();
                FLASHBANG_READY = false;
                //flashbang
            }
        }
    }

    @Override
    public void run() {
        if(RUN_READY){
            RUN_ACTIVE = true;
            RUN_READY = false;
            RUN_COOLDOWN_TIMER = System.currentTimeMillis();
            RUN_DURATION_TIMER = System.currentTimeMillis();
            MOVEMENT_SPEED += RUN_BOOST;
        }
    }

    @Override
    public void resupply() {
        AMMO_S1 = ASSAULT_AMMO_S1;
    }

    @Override
    public void move(double angle) {

    }

    @Override
    public void tick() {
        super.tick();
        if(!FLASHBANG_READY) {
            if (FLASHBANG_TIMER + (1000 / FLASHBANG_FIRE_SPEED) < System.currentTimeMillis()) {
                FLASHBANG_READY = true;
            }
        }
        if(!RUN_READY) {
        if (RUN_COOLDOWN_TIMER + (RUN_COOLDOWN * 1000) < System.currentTimeMillis()) {
            RUN_READY = true;
        }
        }

        if(RUN_ACTIVE) {
            if (RUN_DURATION_TIMER + (RUN_DURATION * 1000) < System.currentTimeMillis()) {
               MOVEMENT_SPEED -=RUN_BOOST;
               RUN_ACTIVE = false;
            }
        }


    }

    @Override
    public void render(SpriteBatch spriteBatch) {
            character.draw(spriteBatch);
    }
}
