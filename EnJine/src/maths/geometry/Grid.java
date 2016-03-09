package maths.geometry;

/**
 * Created by Omer on 3/8/2016.
 */
public class Grid {

    public int width;
    public int height;
    public int col;
    public int row;

    public int tileWidth;
    public int tileHeight;

    public Grid(int width,int height,int col,int row){
        this.width = width;
        this.height = height;
        this.col  = col;
        this.row = row;

        tileWidth  = Math.round(width/col);
        tileHeight = Math.round(height/row);
    }
}
