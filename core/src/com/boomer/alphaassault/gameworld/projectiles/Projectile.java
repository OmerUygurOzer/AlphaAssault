package com.boomer.alphaassault.gameworld.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.UnitBase;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.memoryutils.Poolable;

/**
 * Created by Omer on 12/20/2015.
 */
public abstract class Projectile extends Entity implements Renderable,Poolable {

    protected static final int TRAVEL_RANGE_MULTIPLIER = 10;

    protected int viewType;

    protected UnitBase source;

    protected float travelSpeed; //PER SECOND
    protected float directionAngle;
    protected float traveledDistance;
    protected float travelRange;

    protected BDrawable drawable;


    protected Projectile(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
        traveledDistance = 0f;
        isSolid = false;
    }


    public void setSpeed(float _speed){travelSpeed = _speed;}

    public void setDirectionAngle(float _angle){directionAngle  = _angle;}

    public void setRange(float _range){travelRange = _range * TRAVEL_RANGE_MULTIPLIER;}

    public void setSource(UnitBase _source){source = _source;}

    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId,depth, drawable);

    }

    @Override
    public void removeFromRenderState() {
        RenderStateManager.updatingStatePointer.removeElement(referenceId,depth);
    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }




}

