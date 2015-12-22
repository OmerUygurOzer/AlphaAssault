package com.boomer.alphaassault.gameworld;



import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.gamelogic.GameMath;
import com.boomer.alphaassault.gameworld.mapfeatures.*;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.graphics.Renderable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public class Map implements Renderable{

    //REFERENCE
    private long referenceId;
    private int viewType;

    //MAP CONSTANTS
    private static final int SCALE = 2;

    //SIZE CONSTANTS
    public static final int SIZE_SMALL = 0;
    public static final int SIZE_MEDIUM = 1;
    public static final int SIZE_LARGE = 2;

    private static final int SIDE_SMALL = 128;
    private static final int SIDE_MEDIUM = 256;
    private static final int SIDE_LARGE = 512;

    private static final int TILE_SIZE = 16;

    //WORLD INSTANCE
    GameWorld world;

    //MAP BASE CONSTANTS

    private int[][] featureTiles;
    private List<MapFeature> mapFeatures;

    private int width;
    private int height;

    private BSprite mapBase;

    //FEATURE CONSTANTS
    private static final int FEATURE_EMPTY = 0;
    private static final int FEATURE_BUSH = 1;
    private static final int FEATURE_CRATE = 2;
    private static final int FEATURE_ROCKS = 3;
    private static final int FEATURE_TREE = 4;
    private static final int FEATURE_WATER = 5;
    private static final int FEATURE_PLAYER_HQ = 10;


    public Map(int _size,GameWorld _world){
        world = _world;
        mapFeatures = new ArrayList<MapFeature>();
        switch(_size){
            case SIZE_SMALL:
                width = SIDE_SMALL * SCALE;
                height = SIDE_SMALL * SCALE;
                break;

            case SIZE_MEDIUM:
                width = SIDE_MEDIUM * SCALE;
                height = SIDE_MEDIUM * SCALE;
                break;

            case SIZE_LARGE:
                width = SIDE_LARGE * SCALE;
                height = SIDE_LARGE * SCALE;
                break;

            default:
                //DO NOTHING
                break;
        }


        featureTiles = new int[width/TILE_SIZE][height/TILE_SIZE];
        for(int x=0;x< width/TILE_SIZE;x++){
            for(int y=0;y< height/TILE_SIZE;y++){
                featureTiles[x][y]=FEATURE_EMPTY;
            }
        }
        generateMap();
    }

    private void generateMap(){
            Random random = new Random();

            int min = 0;
            int max = 1;

            int tileTextureWidth = Resource.getTextureRegions(Resource.BACKGROUND)[0][0].getRegionWidth();
            int tileTextureHeight =  Resource.getTextureRegions(Resource.BACKGROUND)[0][0].getRegionHeight();

            Pixmap tileGrass = new Pixmap(128,128, Pixmap.Format.RGBA8888);
            Pixmap tileBadLands = new Pixmap(128,128,Pixmap.Format.RGBA8888);
            Resource.getTextureRegions(Resource.BACKGROUND)[0][0].getTexture().getTextureData().prepare();
            Pixmap tilesAll = Resource.getTextureRegions(Resource.BACKGROUND)[0][0].getTexture().getTextureData().consumePixmap();

            for(int x = 0;x<tileTextureWidth;x++){
                for(int y = 0;y < tileTextureHeight;y++){
                    int pixel = tilesAll.getPixel(x,y);
                    tileGrass.drawPixel(x,y,pixel);

                }
            }

            for(int x = 128;x<tileTextureWidth+128;x++){
                for(int y = 0;y < tileTextureHeight;y++){
                int pixel = tilesAll.getPixel(x,y);
                tileBadLands.drawPixel(x-128,y,pixel);
                }
            }

            int scaler  = tileTextureHeight / TILE_SIZE;


            Texture base = new Texture(width*scaler,height*scaler, Pixmap.Format.RGBA8888);
            base.draw(tileBadLands,0,0);

            for(int x=0;x< width/TILE_SIZE;x++){
                for(int y=0;y< height/TILE_SIZE;y++) {
                    int type = random.nextInt((max - min) + 1) + min;
                    switch(type){
                        case 0:
                           base.draw(tileGrass,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                            break;
                        case 1:
                            base.draw(tileBadLands,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                            break;
                        default:
                            //DO NOTHING
                            break;
                    }
                }
            }

            tileGrass.dispose();
            tileBadLands.dispose();
            tilesAll.dispose();

            mapBase = new BSprite(base);
            mapBase.setSize(width,height);
            mapBase.setCenter(width/2,height/2);

            for(int x=0;x< width/TILE_SIZE;x++){
                for(int y=0;y< height/TILE_SIZE;y++) {
                    int feature = random.nextInt((40 - 1) + 1) + 1;
                    if(featureTiles[x][y] == FEATURE_EMPTY){
                    switch (feature) {
                        case FEATURE_BUSH:
                            Bush bush = new Bush(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(bush);
                            featureTiles[x][y] = FEATURE_BUSH;
                            break;
                        case FEATURE_CRATE:
                            Crate crate = new Crate(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(crate);
                            featureTiles[x][y] = FEATURE_CRATE;
                            break;
                        case FEATURE_ROCKS:
                            Rocks rocks = new Rocks(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(rocks);
                            featureTiles[x][y] = FEATURE_ROCKS;
                            break;
                        case FEATURE_TREE:
                            if(y+1<height/TILE_SIZE) {
                                Tree tree = new Tree(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                                mapFeatures.add(tree);
                                featureTiles[x][y] = FEATURE_TREE;
                                featureTiles[x][y + 1] = FEATURE_TREE;
                            }
                            break;
                        case FEATURE_WATER:
                            Water water = new Water(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(water);
                            featureTiles[x][y] = FEATURE_WATER;
                            break;
                        default:
                            break;
                    }
                }
                }
            }



    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean isMoveable(float _x,float _y){
        int tileX = Math.round(((_x - (_x % TILE_SIZE))/TILE_SIZE));
        int tileY = Math.round(((_y - (_y % TILE_SIZE))/TILE_SIZE));
        float centerX = (tileX * TILE_SIZE)+TILE_SIZE/2f;
        float centerY = (tileY * TILE_SIZE)+TILE_SIZE/2f;
        int radius = 0;
        switch(featureTiles[tileX][tileY]){
            case FEATURE_BUSH:
                return true;
            case FEATURE_CRATE:
                radius = Crate.CRATE_RADIUS;
                break;
            case FEATURE_ROCKS:
                radius = Rocks.ROCKS_RADIUS;
                break;
            case FEATURE_TREE:
                radius = Tree.TREE_RADIUS;
                break;
            case FEATURE_WATER:
                radius = Water.WATER_RADIUS;
                break;
        }
        double distance = GameMath.getDistance(_x,_y,centerX,centerY);
        if(distance < radius){
            return false;
        }

        return true;
    }


    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId,RenderState.DEPTH_BASE,mapBase);
        for(MapFeature mapFeature:mapFeatures){
            mapFeature.setViewType(viewType);
            world.addEntity(mapFeature);
           // mapFeature.addToRenderState();
        }
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        referenceId = _referenceId;
    }

    @Override
    public void setViewType(int _cameraType) {
        viewType = _cameraType;}
}
