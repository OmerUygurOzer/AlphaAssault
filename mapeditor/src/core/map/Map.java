package core.map;

import com.badlogic.gdx.graphics.g2d.Sprite;

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





    private List<Entity> entityList;

    public Map(int _size){
        size = _size;

        width = size * TILE_SIZE;
        height = width;


        baseTiles = new int[size];

        for(int y = 0;y<size ; y++){
            for(int x = 0; x<size ; x++){
                Random random = new Random();
                int min = 1;
                int max = 2;
                baseTiles[x + y *size] = random.nextInt((max - min) + 1) + min;;
            }
        }


    }

    public void addEntity(int _id){
        Entity entity = EntityParser.parse(_id);

    }

}
