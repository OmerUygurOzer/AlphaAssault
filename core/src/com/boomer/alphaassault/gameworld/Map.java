package com.boomer.alphaassault.gameworld;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Renderable;

import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public class Map implements Renderable{

    //REFERENCE
    private long referenceId;
    private int cameraType;

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
            Random random = new Random();//int randomNum = rand.nextInt((max - min) + 1) + min;
            int min = 0;
            int max = 1;
            for(int x=0;x< width/Tile.TILE_SIZE;x++){
                for(int y=0;y< height/Tile.TILE_SIZE;y++){
                        int type = random.nextInt((max-min)+1)+min;
                        backgroundTiles[x][y] = new Tile(type);
                        backgroundTiles[x][y].image.setPosition(x*Tile.TILE_SIZE,y*Tile.TILE_SIZE);
                }
            }



    }


    @Override
    public void addToRenderState() {
        long baseID = referenceId;
        for(int x=0;x< width/Tile.TILE_SIZE;x++){
            for(int y=0;y< height/Tile.TILE_SIZE;y++){
                baseID++;
                RenderStateManager.addElement(cameraType,baseID, RenderState.DEPTH_BASE,backgroundTiles[x][y].image);
            }
        }
    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setViewType(int _cameraType) {cameraType = _cameraType;}
}
