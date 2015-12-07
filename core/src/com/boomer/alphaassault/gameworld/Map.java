package com.boomer.alphaassault.gameworld;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.mapfeatures.*;
import com.boomer.alphaassault.graphics.RenderState;
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


    private Tile[][] backgroundTiles;
    private List<MapFeature> mapFeatures;

    private int width;
    private int height;

    private class Tile{
        private static final int TILE_STANDARD = 0;
        private static final int TILE_BADLANDS = 1;

        public  static final int TILE_SIZE = 20;

        private static final int TEXTURE_REGION_SIZE = 100;
        private TextureRegion[][] tileRegions;

        public Sprite image;
        public int type;

        public Tile(int _type){
            tileRegions = TextureRegion.split(Resource.getTexture(Resource.TEXTURE_BACKGROUND),TEXTURE_REGION_SIZE,TEXTURE_REGION_SIZE);
            type = _type;
            switch(_type){
                case TILE_STANDARD:
                    image = new Sprite(tileRegions[0][TILE_STANDARD]);
                    break;
                case TILE_BADLANDS:
                    image = new Sprite(tileRegions[0][TILE_BADLANDS]);
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

        backgroundTiles = new Tile[width/Tile.TILE_SIZE][height/Tile.TILE_SIZE];

        generateMap();

    }

    private void generateMap(){
            Random random = new Random();
            int min = 0;
            int max = 1;
            for(int x=0;x< width/Tile.TILE_SIZE;x++){
                for(int y=0;y< height/Tile.TILE_SIZE;y++){
                        int type = random.nextInt((max-min)+1)+min;
                        backgroundTiles[x][y] = new Tile(type);
                        backgroundTiles[x][y].image.setPosition(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE);
                        int feature = random.nextInt((20-1)+1)+1;
                        switch (feature){
                            case 1:
                               Bush bush = new Bush(new Location(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE));
                               mapFeatures.add(bush);
                                break;
                            case 2:
                               Crate crate = new Crate(new Location(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE));
                               mapFeatures.add(crate);
                               break;
                            case 3:
                               Rocks rocks = new Rocks(new Location(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE));
                               mapFeatures.add(rocks);
                               break;
                            case 4:
                                Tree tree = new Tree(new Location(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE));
                                mapFeatures.add(tree);
                            case 5:
                               Water water = new Water(new Location(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE));
                               mapFeatures.add(water);
                               break;
                            default:
                               break;
                        }
                }
            }



    }


    @Override
    public void addToRenderState() {
        long baseID = referenceId;
        for(int x=0;x< width/Tile.TILE_SIZE;x++){
            for(int y=0;y< height/Tile.TILE_SIZE;y++){
                baseID++;
                RenderStateManager.addElement(viewType,baseID, RenderState.DEPTH_BASE,backgroundTiles[x][y].image);
            }
        }
        for(MapFeature mapFeature:mapFeatures){
            baseID++;
            mapFeature.setViewType(viewType);
            mapFeature.setReferenceID(baseID);
            mapFeature.addToRenderState();
           // RenderStateManager.addElement(viewType,baseID, RenderState.DEPTH_SURFACE,mapFeature.featureSprite);
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
