package com.boomer.alphaassault.gameworld.units.assaulttrooper;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.skills.Flashbang;
import com.boomer.alphaassault.gameworld.units.skills.Run;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
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


    //SKILLS
    Flashbang flashbang  = new Flashbang();
    Run run = new Run();


    public AssaultTrooper(int _team, Location _location) {
        super(_team, _location);
        setLocation(_location.x,_location.y);

        //CALCULATE/LOAD ANIMATIONS
        TextureRegion[][] framesAll = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_ASSAULT_TROOPER),138,192);
        bAnimation = new BAnimation(framesAll);
        bAnimation.setSize(UNIT_SIZE,UNIT_SIZE);
        bAnimation.setCenter(0f,0f);
        bAnimation.setFacingAngle(facingAngle);
    }

    @Override
    public void fire() {

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
    public void move(float _deltaTime,float _x, float _y,double _angle) {
        facingAngle = _angle;
        bAnimation.setFacingAngle(_angle);
        bAnimation.setCenter(_x,_y);
        RenderStateManager.updatingState.updateElement(getReferenceID(), RenderState.DEPTH_SURFACE, bAnimation);
    }

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {
        //super.update();

    }



}
