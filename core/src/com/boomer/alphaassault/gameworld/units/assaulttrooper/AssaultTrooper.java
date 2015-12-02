package com.boomer.alphaassault.gameworld.units.assaulttrooper;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.assaulttrooper.AssaultTrooperSkillSet;
import com.boomer.alphaassault.gameworld.units.skills.Flashbang;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class AssaultTrooper extends Unit implements AssaultTrooperSkillSet {

    //ASSAULT TROOPER
    public static final int ASSAULT_TROOPER_HP = 12;
    public static final int ASSAULT_TROOPER_RANGE = 8;
    public static final int ASSAULT_TROOPER_SIGHT = 8;
    public static final int ASSAULT_TROOPER_FIRE_SPEED = 4;
    public static final int ASSAULT_TROOPER_DAMAGE = 4;
    public static final int ASSAULT_TROOPER_MOVEMENT_SPEED = 8;
    public static final int ASSAULT_TROOPER_AMMO_S1 = 2;
    public static final int ASSAULT_TROOPER_AMMO_S2 = 0;



    Flashbang flashbang  = new Flashbang();



    public AssaultTrooper(int _team, Location _location) {
        super(_team, _location);


        unitSprite = new Sprite(Resource.getTexture(Resource.TEXTURE_ASSAULT_TROOPER));
        unitSprite.setSize(radius *5, radius *5);
        unitSprite.setCenter(location.x, location.y);




    }

    //ASSAULT TROOPER SKILL SET
    @Override
    public void flashbang() {
    }

    @Override
    public void run() {

    }
    //UNIT
    @Override
    public void resupply() {

    }

    @Override
    public void move(double angle) {

    }

    //UPDATEABLE
    @Override
    public void update() {
        super.update();



    }



}
