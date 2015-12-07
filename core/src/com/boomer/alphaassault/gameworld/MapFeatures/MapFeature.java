package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public abstract class MapFeature implements Renderable{

    //TYPE PROPERTIES
    public static final int CRATE_RADIUS = 5;
    public static final int BUSH_RADIUS = 8;
    public static final int ROCKS_RADIUS = 5;
    public static final int TREE_RADIUS = 10;
    public static final int WATER_RADIUS = 5;

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
    public Sprite featureSprite;
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
        RenderStateManager.addElement(viewType,referenceId, RenderState.DEPTH_SURFACE, featureSprite);
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