package com.boomer.alphaassault.gameworld.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.settings.GameSettings;


/**
 * Created by Omer on 12/20/2015.
 */
public class Bullet extends Projectile {


    private static final int SIZE = 15;
    private static final int BULLET_RADIUS = 10;

    private int damage;
    private int collisionLimit;
    private int currentCollisionCount;


    public Bullet(Vector2 _center, int _depth, GameWorld _world,double _angle) {
        super(_center, _depth, _world);
        currentCollisionCount = 0;
        image = new BSprite(Resource.getTextureRegions(Resource.BULLET)[0][0]);
        ((BSprite) image).setSize(SIZE, SIZE);
        ((BSprite)image).setCenter(center.x, center.y);
        radius = BULLET_RADIUS;
    }

    public Bullet(GameWorld _world){
        super(new Vector2(), RenderState.DEPTH_SURFACE,_world);
        currentCollisionCount = 0;
        image = new BSprite(Resource.getTextureRegions(Resource.BULLET)[0][0]);
        ((BSprite) image).setSize(SIZE, SIZE);
        ((BSprite)image).setCenter(center.x, center.y);
        radius = BULLET_RADIUS;
    }


    public void setDamage(int _damage) {
        damage = _damage;
    }

    public void setCollisionLimit(int _limit) {
        collisionLimit = _limit;
    }

    @Override
    public void update(float _deltaTime) {
     if(!removed) {
        double xMovement = Math.sin(Math.toRadians(directionAngle)) * travelSpeed / GameSettings.UPS;
        double yMovement = Math.cos(Math.toRadians(directionAngle)) * travelSpeed / GameSettings.UPS;
         center.x = center.x + (float)xMovement;
        center.y = center.y + (float)yMovement;
         ((BSprite)image).setCenter(center.x, center.y);
        traveledDistance += travelSpeed / GameSettings.UPS;
        if (traveledDistance >= travelRange) {
            removed = true;
        }

       RenderStateManager.updatingStatePointer.updateElement(referenceId, viewType, image);
    }
    }
    @Override
    public void uponCollision(Entity _entity) {
        if(currentCollisionCount == collisionLimit){markRemoved();return;}
        currentCollisionCount++;
        _entity.receiveHit(damage,source);


    }

    @Override
    public void receiveHit(int _hit,Unit _source) {

    }

    @Override
    public void reset() {
        traveledDistance = 0f;
        currentCollisionCount = 0;
        markNew();
    }
}
