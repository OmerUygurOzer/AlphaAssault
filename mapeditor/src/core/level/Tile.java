package core.level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/12/2016.
 */
public class Tile{
    private static final int N  = 0;
    private static final int NE = 1;
    private static final int E  = 2;
    private static final int SE = 3;
    private static final int S  = 4;
    private static final int SW = 5;
    private static final int W  = 6;
    private static final int NW = 7;

    private List<Integer> tileCrossings = new ArrayList<Integer>();
    private String base;
    private String feature;
    private int featureVariety;

    public int x;
    public int y;
    public int width;
    public int height;

    public Tile(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width  = width;
        this.height = height;
    }

    public void addCrossing(int crossing){
        if(!tileCrossings.contains(crossing))
            tileCrossings.add(crossing);
    }

    public void addBase(String base){
        this.base = base;
    }

    public void addFeature(String feature,int variety){
        this.feature = feature;
        this.featureVariety = variety;
    }



}
