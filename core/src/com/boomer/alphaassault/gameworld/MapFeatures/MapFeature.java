package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

/**
 * Created by Omer on 11/25/2015.
 */
public abstract class MapFeature implements Renderable{
    //TYPE DETAILS
    public static final int FEATURE_TYPE_CRATE = 0;
    public static final int FEATURE_TYPE_BUSH = 1;
    public static final int FEATURE_TYPE_ROCKS = 2;
    public static final int FEATURE_TYPE_TREE = 3;
    public static final int FEATURE_TYPE_WATER = 4;


    //TYPE PROPERTIES
    public static final int CRATE_RADIUS = 5;
    public static final int BUSH_RADIUS = 8;
    public static final int ROCKS_RADIUS = 5;
    public static final int TREE_RADIUS = 10;
    public static final int WATER_RADIUS = 5;

    //BLOCKING TYPE
    protected boolean BLOCKS_MOVEMENT;
    protected boolean BLOCKS_BULLETS;
    protected boolean BLOCKS_AERIAL; //Grenades ,flashbang etc...
    protected boolean BLOCKS_DAMAGE;

    protected int RADIUS;

    protected boolean DESTROYABLE;

    //MECHANIC/GRAPHICAL DETAILS
    protected Location LOCATION;
    private long REFERENCE_ID;
    private Sprite FEATURE_SPRITE;
    private int CAMERA_TYPE;

    public MapFeature(int _type, Location _location) {

        REFERENCE_ID = System.currentTimeMillis();
        LOCATION = _location;

        switch (_type){
            case FEATURE_TYPE_CRATE:
                DESTROYABLE = true;
                BLOCKS_MOVEMENT = true;
                BLOCKS_BULLETS = true;
                BLOCKS_AERIAL = false;
                BLOCKS_DAMAGE = false;
                RADIUS = CRATE_RADIUS;
                break;
            case FEATURE_TYPE_BUSH:
                DESTROYABLE = false;
                BLOCKS_MOVEMENT = false;
                BLOCKS_BULLETS = false;
                BLOCKS_AERIAL = false;
                BLOCKS_DAMAGE = false;
                RADIUS = BUSH_RADIUS;
                break;
            case FEATURE_TYPE_ROCKS:
                DESTROYABLE = false;
                BLOCKS_MOVEMENT = true;
                BLOCKS_BULLETS = true;
                BLOCKS_AERIAL = false;
                BLOCKS_DAMAGE = true;
                RADIUS = ROCKS_RADIUS;
                break;
            case FEATURE_TYPE_TREE:
                DESTROYABLE = false;
                BLOCKS_MOVEMENT = true;
                BLOCKS_BULLETS = true;
                BLOCKS_AERIAL = true;
                BLOCKS_DAMAGE = true;
                RADIUS = TREE_RADIUS;
                break;
            case FEATURE_TYPE_WATER:
                DESTROYABLE = false;
                BLOCKS_MOVEMENT = true;
                BLOCKS_BULLETS = false;
                BLOCKS_AERIAL = false;
                BLOCKS_DAMAGE = false;
                RADIUS = WATER_RADIUS;
                break;
             default:
                break;
        }

        addToRenderState();
    }

    public boolean isDestroyable(){
        return DESTROYABLE;
    }
    public boolean doesBlockMovement(){
        return BLOCKS_MOVEMENT;
    }
    public boolean doesBlockBullets(){
        return BLOCKS_BULLETS;
    }
    public boolean doesBlockAerial(){
        return BLOCKS_AERIAL;
    }
    public boolean doesBlockDamage(){
        return BLOCKS_DAMAGE;
    }
    public Location getLocation(){
        return LOCATION;
    }
    public int getRadius(){
        return RADIUS;
    }

    @Override
    public void setViewType(int _cameraType) {
        CAMERA_TYPE = _cameraType;
    }

    @Override
    public void addToRenderState() {
        //RenderStateManager.addElement(CAMERA_TYPE,REFERENCE_ID,FEATURE_SPRITE);
    }

    @Override
    public long getReferenceID() {
        return REFERENCE_ID;
    }
}
