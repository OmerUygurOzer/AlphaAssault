package level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/17/2016.
 */
public class Level {

    private int width;
    private int height;
    private int tileSize;

    private Tile[] tiles;

    public Level(int width, int height, int tileSize) {

        this.width = width;
        this.height = height;
        this.tileSize = tileSize;

        int xTiles = Math.round(width/tileSize);
        int yTiles = Math.round(height/tileSize);

        tiles = new Tile[xTiles * yTiles];


    }


}
