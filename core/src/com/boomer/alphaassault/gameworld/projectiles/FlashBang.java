package com.boomer.alphaassault.gameworld.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.visuals.Smoke;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/23/2015.
 */
public class FlashBang extends Projectile {

    private static final int SIZE = 15;

    private int AOE;
    private float duration;

    private long initTime;
    private boolean smokeActive;

    public FlashBang(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
        drawable = new BSprite(Resource.getTextureRegions(Resource.FLASHBANG)[0][0]);
        ((BSprite) drawable).setSize(SIZE, SIZE);
        ((BSprite)drawable).setCenter(center.x, center.y);
        smokeActive = false;
    }

    public void setDuration (float _duration){
        duration = _duration;
    }

    public void activateSmoke(){
        smokeActive = true;
        initTime = System.currentTimeMillis();
        Smoke smokeScreen = new Smoke(new Vector2(center.x,center.y), RenderState.DEPTH_AIR,world);
        smokeScreen.setViewType(viewType);
        smokeScreen.setCycleUponFinish(true);
        smokeScreen.setDuration(duration);
        world.addEntity(smokeScreen);


    }

    public void setAOE(int _aoe){
        AOE = _aoe;
    }

    @Override
    public void uponCollision(Entity _entity) {

    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }

    @Override
    public void reset() {
        markNew();
    }

    @Override
    public void update(float _deltaTime) {
        if(!removed && !smokeActive){
            double xMovement = Math.sin(Math.toRadians(directionAngle)) * travelSpeed / GameSystem.UPS;
            double yMovement = Math.cos(Math.toRadians(directionAngle)) * travelSpeed / GameSystem.UPS;
            center.x = center.x + (float)xMovement;
            center.y = center.y + (float)yMovement;
            ((BSprite)drawable).setCenter(center.x,center.y);
            traveledDistance += travelSpeed / GameSystem.UPS;
            if (traveledDistance >= travelRange) {
                activateSmoke();
                return;
            }

            RenderStateManager.updatingStatePointer.updateElement(referenceId, viewType, drawable);
        }
        if(smokeActive){
            if(System.currentTimeMillis() - initTime >= duration){
                smokeActive = false;
                markRemoved();
            }

        }

    }


}
