package com.boomer.alphaassault.gameworld.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.UnitBase;
import com.boomer.alphaassault.gameworld.visuals.Explosion;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/24/2015.
 */
public class Rocket extends Projectile {

    private static final int SIZE = 60;

    private static final int ROCKET_RADIUS = 10;

    private int damage;
    private float AOE;



    public Rocket(Vector2 _center, int _depth, GameWorld _world,float _angle) {
        super(_center, _depth, _world);
        super.setDirectionAngle(_angle);
        drawable = new BAnimation(Resource.getTextureRegions(Resource.ROCKET), BAnimation.Type.DIRECTIONAL);
        ((BAnimation)drawable).setCenter(center.x,center.y);
        ((BAnimation)drawable).setSize(SIZE,SIZE);
        ((BAnimation)drawable).setFacingAngle(_angle);
        ((BAnimation)drawable).setSecondsPerFrame(1f/4f);
        radius = ROCKET_RADIUS;
    }

    public void setDamage(int _damage){
        damage = _damage;
    }

    private void explode(Vector2 explodeLoc){
        Explosion explosion = new Explosion(new Vector2(center.x,center.y), RenderState.DEPTH_AIR,world);
        explosion.setViewType(world.viewType);
        world.addEntity(explosion);
    }

    @Override
    public void uponCollision(Entity _entity) {
            explode(center);
            markRemoved();

    }

    @Override
    public void receiveHit(int _hit, UnitBase _source) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void update(float _deltaTime) {
        if(!removed){
            double xMovement = Math.sin(Math.toRadians(directionAngle)) * travelSpeed / GameSystem.UPS;
            double yMovement = Math.cos(Math.toRadians(directionAngle)) * travelSpeed / GameSystem.UPS;
            center.x = center.x + (float)xMovement;
            center.y = center.y + (float)yMovement;
            ((BAnimation)drawable).setCenter(center.x,center.y);
            traveledDistance += travelSpeed / GameSystem.UPS;
            if (traveledDistance >= travelRange) {
                markRemoved();
                explode(center);
                return;
            }
            ((BAnimation) drawable).update(_deltaTime);
            RenderStateManager.updatingStatePointer.updateElement(referenceId, viewType, drawable);
        }
    }
}
