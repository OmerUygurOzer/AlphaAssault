package core.map;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import core.Resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Omer on 1/3/2016.
 */
public class Map {

    private static final int TILE_SIZE = 16;
    public static final int SMALL =  16;
    public static final int MEDIUM = 24;
    public static final int LARGE = 32;

    private int size;

    private int width;
    private int height;

    private int[] baseTiles;
    private Sprite mapBase;




    private List<Entity> entityList;

    public Map(int _size){
        size = _size;

        width = size * TILE_SIZE;
        height = width;


        baseTiles = new int[size * size];
        entityList = new ArrayList<Entity>();
        for(int y = 0;y<size ; y++){
            for(int x = 0; x<size ; x++){
                Random random = new Random();
                int min = 1;
                int max = 2;
                baseTiles[x + y *x] = random.nextInt((max - min) + 1) + min;;


            }
        }

        Random random = new Random();

        int min = 0;
        int max = 1;

        Pixmap tileGrass;
        Pixmap tileBadlands;

        tileGrass = new Pixmap(80,80, Pixmap.Format.RGBA8888);
        tileBadlands = new Pixmap(80,80,Pixmap.Format.RGBA8888);
        TextureRegion grass = Resources.getTextureRegions(Resources.BACKGROUND)[0][0];
        TextureRegion badlands = Resources.getTextureRegions(Resources.BACKGROUND)[0][1];
        Resources.getTexture(Resources.IN_GAME).getTextureData().prepare();
        Pixmap tilesAll = Resources.getTexture(Resources.IN_GAME).getTextureData().consumePixmap();

        int regionSize = grass.getRegionHeight();

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

        int scaler  = regionSize / TILE_SIZE;


        Texture base = new Texture(width*scaler,height*scaler, Pixmap.Format.RGBA8888);

        for(int x=0;x< width/TILE_SIZE;x++){
            for(int y=0;y< height/TILE_SIZE;y++) {
                int type = random.nextInt((max - min) + 1) + min;
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


        tilesAll.dispose();

        mapBase = new Sprite(base);
        mapBase.setSize(width,height);
        mapBase.setCenter(width/2,height/2);

    }

    public void addEntity(int _id,int _x,int _y){
        Entity entity = EntityParser.parse(_id);
        entity.x = _x;
        entity.y = _y;
        entity.image.setCenter(_x,_y);
        entityList.add(entity);
    }

    public void draw(SpriteBatch _batch){
        mapBase.draw(_batch);
        for(Entity entity:entityList){
            entity.image.draw(_batch);
        }
    }

}
