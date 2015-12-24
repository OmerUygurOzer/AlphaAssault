package com.boomer.alphaassault.gameworld.units.assaulttrooper;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.skills.Fire_AssaultRifle;
import com.boomer.alphaassault.gameworld.units.skills.Fire_Flashbang;
import com.boomer.alphaassault.gameworld.units.skills.Fire_Rocket;
import com.boomer.alphaassault.gameworld.units.skills.Run;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

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

    private static final int ASSAULT_RIFLE_KEY = 0;
    private static final int RUN_KEY = 1;
    private static final int FLASHBANG_KEY = 2;
    private static final int ROCKET_KEY = 3;

    public AssaultTrooper(int _team, Vector2 _center,GameWorld _world) {
        super(_team, _center,_world);
        HP = ASSAULT_TROOPER_HP;
        range = ASSAULT_TROOPER_RANGE;
        sight = ASSAULT_TROOPER_SIGHT;
        damage = ASSAULT_TROOPER_DAMAGE;
        baseMovementSpeed = ASSAULT_TROOPER_MOVEMENT_SPEED;

        //CALCULATE/LOAD ANIMATIONS
        TextureRegion[][] framesAll = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_REGION_ASSAULT_TROOPER),1024/6,2048/8);
        bAnimation = new BAnimation(framesAll, BAnimation.Type.DIRECTIONAL);
        bAnimation.setSize(UNIT_SIZE,UNIT_SIZE);
        bAnimation.setCenter(center.x,center.y);
        bAnimation.setFacingAngle(facingAngle);
        bAnimation.setSecondsPerFrame(1f/10f);

        //ADD SKILLS
        Fire_AssaultRifle fire = new Fire_AssaultRifle(ASSAULT_RIFLE_KEY);
        fire.setUser(this);
        Run run = new Run(RUN_KEY);
        run.setUser(this);
        Fire_Flashbang fireFlashbang = new Fire_Flashbang(FLASHBANG_KEY);
        fireFlashbang.setUser(this);

        fire.setWorld(_world);
        run.setWorld(_world);
        fireFlashbang.setWorld(_world);

        Fire_Rocket fireRocket = new Fire_Rocket(ROCKET_KEY);
        fireRocket.setUser(this);
        fireRocket.setWorld(_world);

        addSkill(fire);
        addSkill(run);
        addSkill(fireFlashbang);
        addSkill(fireRocket);

        //ADD THE SUPPLIES
        setSupplies(FLASHBANG_KEY, fireFlashbang.getSupply());
        setSupplies(ROCKET_KEY,fireRocket.getSupply());


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
        setCenter(_x,_y);
        RenderStateManager.updatingStatePointer.updateElement(getReferenceID(), RenderState.DEPTH_SURFACE, bAnimation);
    }

    //UPDATEABLE
    @Override
    public void update(float _deltaTime) {
        super.update(_deltaTime);

    }


    @Override
    public void uponCollision(Entity _entity) {
        //NOTHING
    }

    @Override
    public void receiveHit(int _hit,Unit _source) {
        HP -= _hit;
        if(HP <= 0){markRemoved();
        //RAISE EVENT FOR THE PLAYER OF THE SOURCE TO GET A KILL SCORE
        }
    }
}
