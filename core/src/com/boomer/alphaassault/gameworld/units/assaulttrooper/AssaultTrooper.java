package com.boomer.alphaassault.gameworld.units.assaulttrooper;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.skills.Fire;
import com.boomer.alphaassault.gameworld.units.skills.Flashbang;
import com.boomer.alphaassault.gameworld.units.skills.Run;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class AssaultTrooper extends Unit {

    //ASSAULT TROOPER
    public static final int ASSAULT_TROOPER_HP = 12;
    public static final int ASSAULT_TROOPER_RANGE = 8;
    public static final int ASSAULT_TROOPER_SIGHT = 8;
    public static final int ASSAULT_TROOPER_FIRE_SPEED = 4;
    public static final int ASSAULT_TROOPER_DAMAGE = 4;
    public static final int ASSAULT_TROOPER_MOVEMENT_SPEED = 8;
    public static final int ASSAULT_TROOPER_FLASHBANG_MAX = 2;

    private static final int FIRE_KEY = 0;
    private static final int RUN_KEY = 1;
    private static final int FLASHBANG_KEY = 2;

    public AssaultTrooper(int _team, Location _location) {
        super(_team, _location);
        setLocation(_location.x,_location.y);

        HP = ASSAULT_TROOPER_HP;
        range = ASSAULT_TROOPER_RANGE;
        sight = ASSAULT_TROOPER_SIGHT;
        damage = ASSAULT_TROOPER_DAMAGE;
        baseMovementSpeed = ASSAULT_TROOPER_MOVEMENT_SPEED;

        //CALCULATE/LOAD ANIMATIONS
        TextureRegion[][] framesAll = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_REGION_ASSAULT_TROOPER),138,192);
        bAnimation = new BAnimation(framesAll);
        bAnimation.setSize(UNIT_SIZE,UNIT_SIZE);
        bAnimation.setCenter(_location.x,_location.y);
        bAnimation.setFacingAngle(facingAngle);
        bAnimation.setSecondsPerFrame(1f/10f);

        //ADD SKILLS
        Fire fire = new Fire(0);
        fire.setUser(this);
        Run run = new Run(1);
        run.setUser(this);
        Flashbang flashbang = new Flashbang(2);
        flashbang.setUser(this);


        addSkill(FIRE_KEY,fire);
        addSkill(RUN_KEY,run);
        addSkill(FLASHBANG_KEY,flashbang);

        //ADD THE SUPPLIES
        setSupplies(FLASHBANG_KEY,flashbang.getSupply());



    }


    //UNIT
    @Override
    public void resupply() {
       for(int key: supplies.keySet()){
           supplies.get(key).count = supplies.get(key).COUNT_MAX;
       }
    }

    @Override
    public void move(float _deltaTime,float _x, float _y,double _angle) {
        facingAngle = _angle;
        bAnimation.setFacingAngle(_angle);
        bAnimation.setCenter(_x,_y);
        bAnimation.update(_deltaTime);
        RenderStateManager.updatingState.updateElement(getReferenceID(), RenderState.DEPTH_SURFACE, bAnimation);
    }

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {
        super.update(_deltaTime);

    }



}
