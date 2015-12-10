package com.boomer.alphaassault.gameworld;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.mapfeatures.*;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;
import com.boomer.alphaassault.utilities.Renderable;

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

    private static final int SIDE_SMALL = 200;
    private static final int SIDE_MEDIUM = 300;
    private static final int SIDE_LARGE = 400;

    //TILE CONSTANTS

    private int[][] featureTiles;
    private Tile[][] baseTiles;
    private List<MapFeature> mapFeatures;

    private int width;
    private int height;

    //FEATURE CONSTANTS
    private static final int FEATURE_EMPTY = 0;
    private static final int FEATURE_BUSH = 1;
    private static final int FEATURE_CRATE = 2;
    private static final int FEATURE_ROCKS = 3;
    private static final int FEATURE_TREE = 4;
    private static final int FEATURE_WATER = 5;
    private static final int FEATURE_PLAYER_BASE = 10;

    private class Tile{
        private static final int TILE_STANDARD = 0;
        private static final int TILE_BADLANDS = 1;

        public  static final int TILE_SIZE = 20;

        private static final int TEXTURE_REGION_SIZE = 100;
        private TextureRegion[][] tileRegions;

        public BSprite image;
        public int type;

        public Tile(int _type){
            tileRegions = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_BACKGROUND),TEXTURE_REGION_SIZE,TEXTURE_REGION_SIZE);
            type = _type;
            switch(_type){
                case TILE_STANDARD:
                    image = new BSprite(tileRegions[0][TILE_STANDARD]);
                    break;
                case TILE_BADLANDS:
                    image = new BSprite(tileRegions[0][TILE_BADLANDS]);
                    break;
                default:
                    //DO NOTHING
                break;
            }
            image.setSize(TILE_SIZE,TILE_SIZE);
        }
    }

    public Map(int _size){
        referenceId = System.currentTimeMillis()+1000;
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

        baseTiles = new Tile[width/Tile.TILE_SIZE][height/Tile.TILE_SIZE];
        featureTiles = new int[width/Tile.TILE_SIZE][height/Tile.TILE_SIZE];
        for(int x=0;x< width/Tile.TILE_SIZE;x++){
            for(int y=0;y< height/Tile.TILE_SIZE;y++){
                featureTiles[x][y]=FEATURE_EMPTY;
            }
        }

        generateMap();

    }

    private void generateMap(){
            Random random = new Random();
            int min = 0;
            int max = 1;
            for(int x=0;x< width/Tile.TILE_SIZE;x++){
                for(int y=0;y< height/Tile.TILE_SIZE;y++) {
                    int type = random.nextInt((max - min) + 1) + min;
                    baseTiles[x][y] = new Tile(type);
                    baseTiles[x][y].image.setPosition(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
                    int feature = random.nextInt((20 - 1) + 1) + 1;
                    if(featureTiles[x][y] == FEATURE_EMPTY){
                    switch (feature) {
                        case FEATURE_BUSH:
                            Bush bush = new Bush(new Location(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE));
                            mapFeatures.add(bush);
                            featureTiles[x][y] = FEATURE_BUSH;
                            break;
                        case FEATURE_CRATE:
                            Crate crate = new Crate(new Location(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE));
                            mapFeatures.add(crate);
                            featureTiles[x][y] = FEATURE_CRATE;
                            break;
                        case FEATURE_ROCKS:
                            Rocks rocks = new Rocks(new Location(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE));
                            mapFeatures.add(rocks);
                            featureTiles[x][y] = FEATURE_ROCKS;
                            break;
                        case FEATURE_TREE:
                            if(y+1<height/Tile.TILE_SIZE) {
                                Tree tree = new Tree(new Location(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE));
                                mapFeatures.add(tree);
                                featureTiles[x][y] = FEATURE_TREE;
                                featureTiles[x][y + 1] = FEATURE_TREE;
                            }
                            break;
                        case FEATURE_WATER:
                            Water water = new Water(new Location(x * Tile.TILE_SIZE, y * Tile.TILE_SIZE));
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
        int tileX = Math.round(((_x - (_x % Tile.TILE_SIZE))/Tile.TILE_SIZE));
        int tileY = Math.round(((_y - (_y % Tile.TILE_SIZE))/Tile.TILE_SIZE));
        float centerX = (tileX * Tile.TILE_SIZE)+Tile.TILE_SIZE/2f;
        float centerY = (tileY * Tile.TILE_SIZE)+Tile.TILE_SIZE/2f;
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
        double distance = Location.getDistance(_x,_y,centerX,centerY);
        if(distance < radius){
            //System.out.println("in");
            return false;
        }

        return true;
    }


    @Override
    public void addToRenderState() {
        long baseID = referenceId;
        for(int x=0;x< width/Tile.TILE_SIZE;x++){
            for(int y=0;y< height/Tile.TILE_SIZE;y++){
                baseID++;
                RenderStateManager.addElement(viewType,baseID, RenderState.DEPTH_BASE, baseTiles[x][y].image);
            }
        }
        for(MapFeature mapFeature:mapFeatures){
            baseID++;
            mapFeature.setViewType(viewType);
            mapFeature.setReferenceID(baseID);
            mapFeature.addToRenderState();
        }
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
