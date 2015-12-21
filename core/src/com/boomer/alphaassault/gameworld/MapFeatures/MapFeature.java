package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.graphics.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public abstract class MapFeature extends Entity implements Renderable{

    //TYPE PROPERTIES

    //BLOCKING TYPE
    protected boolean blocksMovement;
    protected boolean blocksBullets;
    protected boolean blocksAerial; //Grenades ,flashbang etc...
    protected boolean blocksDamage;

    protected int radius;

    protected boolean destroyable;

    //MECHANIC/GRAPHICAL DETAILS
    public BDrawable bDrawable;
    private int viewType;

    public MapFeature(Vector2 _center,GameWorld _world) {
        super(_center,RenderState.DEPTH_SURFACE,_world);
    }
    public final boolean isDestroyable(){
        return destroyable;
    }
    public final boolean doesBlockMovement(){
        return blocksMovement;
    }
    public final boolean doesBlockBullets(){
        return blocksBullets;
    }
    public final boolean doesBlockAerial(){
        return blocksAerial;
    }
    public final boolean doesBlockDamage(){
        return blocksDamage;
    }


    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

    @Override
    public void addToRenderState() {
        RenderStateManager.addElement(viewType,referenceId, RenderState.DEPTH_SURFACE, bDrawable);
    }

    @Override
    public void removeFromRenderState() {
        RenderStateManager.removeElement(referenceId,RenderState.DEPTH_SURFACE);
    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId){
        referenceId=_referenceId;
    }
}
