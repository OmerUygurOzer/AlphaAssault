package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 11/25/2015.
 */
public abstract class MapFeature extends Entity implements Renderable{


    //TYPE PROPERTIES:
    //BLOCKING TYPE
    protected boolean blocksMovement;
    protected boolean blocksBullets;
    protected boolean blocksAerial; //Grenades ,flashbang etc...
    protected boolean blocksDamage;

    protected int radius;

    protected boolean destroyable;

    //MECHANIC/GRAPHICAL DETAILS
    protected BDrawable bDrawable;
    protected TextureRegion image;
    private int viewType;

    public MapFeature(Vector2 _center,GameWorld _world) {
        super(_center,RenderState.DEPTH_SURFACE,_world);
    }

    public void createFromType(String _path){

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
    public void uponCollision(Entity _entity) {
    }


    @Override
    public void update(float _deltaTime) {

    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId, RenderState.DEPTH_SURFACE, bDrawable);
    }

    @Override
    public void removeFromRenderState() {
        RenderStateManager.updatingStatePointer.removeElement(referenceId,RenderState.DEPTH_SURFACE);
    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


}
