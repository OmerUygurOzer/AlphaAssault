package com.boomer.alphaassault.gameworld.map;



import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.mapfeatures.*;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.utilities.GameMath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omer on 11/24/2015.
 */
public class Map implements Renderable{

    //REFERENCE
    private short referenceId;
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

    //GPU MEM CONST
    private Pixmap tileGrass;
    private Pixmap tileBadlands;
    private Texture base;
    private int regionSize;
    private int scaler;

    private List<Vector2> startingPoints;

    //IO
    private MapData data;



    public Map(int _size,GameWorld _world){
        world = _world;
        mapFeatures = new ArrayList<MapFeature>();

        referenceId = GameSystem.obtainReference();

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

        generateResources();
        generateMap();
    }

    private void generateResources(){
        if(tileGrass != null){tileGrass.dispose();}
        if(tileBadlands != null){tileBadlands.dispose();}
        if(base != null){base.dispose();}

        tileGrass = new Pixmap(80,80, Pixmap.Format.RGBA8888);
        tileBadlands = new Pixmap(80,80,Pixmap.Format.RGBA8888);
        TextureRegion grass = Resource.getTextureRegions(Resource.BACKGROUND)[0][0];
        TextureRegion badlands = Resource.getTextureRegions(Resource.BACKGROUND)[0][1];
        Resource.getTexture(Resource.IN_GAME).getTextureData().prepare();
        Pixmap tilesAll = Resource.getTexture(Resource.IN_GAME).getTextureData().consumePixmap();

        regionSize = grass.getRegionHeight();

        for(int x = grass.getRegionX();x<grass.getRegionX()+grass.getRegionWidth();x++){
            for(int y = grass.getRegionY();y < grass.getRegionY()+ grass.getRegionHeight();y++){
                int pixel = tilesAll.getPixel(x,y);
                tileGrass.drawPixel(x-grass.getRegionX(),y-grass.getRegionY(),pixel);
            }
        }

        for(int x = badlands.getRegionX();x<badlands.getRegionX()+badlands.getRegionWidth();x++){
            for(int y = badlands.getRegionY();y <badlands.getRegionY()+ badlands.getRegionHeight();y++){
                int pixel = tilesAll.getPixel(x,y);
                tileBadlands.drawPixel(x-badlands.getRegionX(),y-badlands.getRegionY(),pixel);

            }
        }

       scaler  = regionSize / TILE_SIZE;


        base = new Texture(width*scaler,height*scaler, Pixmap.Format.RGBA8888);

        tilesAll.dispose();

    }

    private void generateMap(){

            startingPoints = new ArrayList<Vector2>();

            Random random = new Random();

            int min = 0;
            int max = 1;


            int [][]tiles = new int[width/TILE_SIZE][height/TILE_SIZE];


            for(int x=0;x< width/TILE_SIZE;x++){
                for(int y=0;y< height/TILE_SIZE;y++) {
                    int type = random.nextInt((max - min) + 1) + min;
                    tiles[x][y] = type;
                    switch(type){
                        case 0:
                           base.draw(tileGrass,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                            break;
                        case 1:
                            base.draw(tileBadlands,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                            break;
                        default:
                            //DO NOTHING
                            break;
                    }
                }
            }




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

        data = new MapData(width/TILE_SIZE,height/TILE_SIZE,tiles,featureTiles);


    }

    public void generateMap(int _width,int _height,int[][] _tiles, int[][] _mapFeatures){
        clearMap();
        width = _width * TILE_SIZE;
        height = _height * TILE_SIZE;

       featureTiles = _mapFeatures;

        generateResources();
        for(int x=0;x< width/TILE_SIZE;x++){
            for(int y=0;y< height/TILE_SIZE;y++) {
             int type = _tiles[x][y];
                switch(type){
                    case 0:
                        base.draw(tileGrass,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                        break;
                    case 1:
                        base.draw(tileBadlands,x*scaler*TILE_SIZE,y*scaler*TILE_SIZE);
                        break;
                    default:
                        //DO NOTHING
                        break;
                }
            }
        }

        for(int x=0;x< width/TILE_SIZE;x++){
            for(int y=0;y< height/TILE_SIZE;y++) {
                    switch (featureTiles[x][y]) {
                        case FEATURE_BUSH:
                            Bush bush = new Bush(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(bush);
                            break;
                        case FEATURE_CRATE:
                            Crate crate = new Crate(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(crate);
                            break;
                        case FEATURE_ROCKS:
                            Rocks rocks = new Rocks(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(rocks);
                            break;
                        case FEATURE_TREE:
                            if(y+1<height/TILE_SIZE) {
                                Tree tree = new Tree(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                                mapFeatures.add(tree);
                            }
                            break;
                        case FEATURE_WATER:
                            Water water = new Water(new Vector2(x * TILE_SIZE, y * TILE_SIZE),world);
                            mapFeatures.add(water);
                            break;
                        default:
                            break;
                    }

            }
        }

        mapBase = new BSprite(base);
        mapBase.setSize(width,height);
        mapBase.setCenter(width/2,height/2);

        data = new MapData(width/TILE_SIZE,height/TILE_SIZE,_tiles,featureTiles);

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
        if(tileX >= featureTiles.length || tileY >= featureTiles[0].length){return false;}
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
        return distance >= radius;
    }

    public void clearTile(float _x,float _y){
        int tileX = Math.round(((_x - (_x % TILE_SIZE))/TILE_SIZE));
        int tileY = Math.round(((_y - (_y % TILE_SIZE))/TILE_SIZE));
        featureTiles[tileX][tileY] = FEATURE_EMPTY;
    }


    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId,RenderState.DEPTH_BASE,mapBase);
        for(MapFeature mapFeature:mapFeatures){
            mapFeature.setViewType(viewType);
            world.addEntity(mapFeature);
        }
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


    @Override
    public void setViewType(int _cameraType) {
        viewType = _cameraType;}

    private void clearMap(){
        RenderStateManager.updatingStatePointer.removeElement(referenceId,RenderState.DEPTH_BASE);
        mapFeatures.clear();
    }

    public void dispose(){
        tileGrass.dispose();
        tileBadlands.dispose();
    }

    public MapData getData(){return data;}
}
