package core.level;

import core.gl.Renderable;

/**
 * Created by Omer on 1/12/2016.
 */
public class Level implements Renderable{
    private String levelName;
    private byte[] levelData;

    private LevelType levelType;

    private boolean tileSizeSet;
    private boolean sizeSet;



    private boolean generated;

    private int width;
    private int heigth;

    private int tileSize;

    private Tile[] tiles;




    public Level(String levelName,LevelType levelType){
        this.levelName = levelName;
        this.levelType = levelType;
        sizeSet = false;
        tileSizeSet = false;
        generated = false;
    }

    public void setSize(int width,int height){
        if(width<=0 || height<=0)
            throw new IllegalArgumentException("Size parameters can not be less than '1'");
        if(generated)
            throw new IllegalStateException("Level already generated.");
        this.width = width;
        this.heigth = height;
        sizeSet = true;
    }

    public void setTileSize(int tileSize){
        if(tileSize<=0)
            throw new IllegalArgumentException("Tile size can not be less than '1'");
        if(generated)
            throw new IllegalStateException("Level already generated.");
        this.tileSize = tileSize;
        tileSizeSet = true;
    }

    public void generate(){
        if (!sizeSet)
            throw new IllegalStateException("Level size has not been set.");
        if(!tileSizeSet)
            throw new IllegalStateException("Tile size has not been set.");


        switch (levelType){
            case MAP_FLAT:
                generateFlatMap();
                break;
            case MAP_ISOMORPHIC:
                generateIsoMap();
                break;
            case PLATFORMER:
                generatePlatform();
                break;

        }

        generated = true;
    }


    @Override
    public void render() {

    }

    private void generateFlatMap(){
        int xTiles = Math.round(width/tileSize);
        int yTiles = Math.round(heigth/tileSize);
        if(xTiles==0)
            throw new IllegalArgumentException("Tile size can not be greater than the width.");
        if(yTiles==0)
            throw new IllegalArgumentException("Tile size can not be greater than the height.");

        int totalSize = xTiles * yTiles;
        tiles = new Tile[totalSize];

       for(int i = 0; i < yTiles ; i++){
           for(int j = 0; j < xTiles ; j++){
               tiles[j + i * yTiles] = new Tile(j*tileSize,i*tileSize,tileSize);
           }
       }

    }

    public void generateIsoMap(){

    }

    public void generatePlatform(){

    }

    public boolean isGenerated() {
        return generated;
    }


    public void saveLevel(){
        LevelIO.saveLevel(this);
    }
    private void toByteArray(){

    }

}
