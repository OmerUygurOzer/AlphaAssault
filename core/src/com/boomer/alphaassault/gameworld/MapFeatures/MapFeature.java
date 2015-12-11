package com.boomer.alphaassault.gameworld.mapfeatures;

import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public abstract class MapFeature implements Renderable{

    //TYPE PROPERTIES

    //BLOCKING TYPE
    protected boolean blocksMovement;
    protected boolean blocksBullets;
    protected boolean blocksAerial; //Grenades ,flashbang etc...
    protected boolean blocksDamage;

    protected int radius;

    protected boolean destroyable;

    //MECHANIC/GRAPHICAL DETAILS
    protected Location location;
    private long referenceId;
    public BDrawable bDrawable;
    private int viewType;

    public MapFeature(Location _location) {
        //referenceId = System.currentTimeMillis();
        location = _location;
    }
    public boolean isDestroyable(){
        return destroyable;
    }
    public boolean doesBlockMovement(){
        return blocksMovement;
    }
    public boolean doesBlockBullets(){
        return blocksBullets;
    }
    public boolean doesBlockAerial(){
        return blocksAerial;
    }
    public boolean doesBlockDamage(){
        return blocksDamage;
    }
    public Location getLocation(){
        return location;
    }
    public int getRadius(){
        return radius;
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
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId){
        referenceId=_referenceId;
    }
}
