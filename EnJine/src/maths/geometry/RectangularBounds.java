package maths.geometry;

import com.badlogic.gdx.math.Vector2;

import java.util.Collection;

/**
 * Created by Omer on 2/5/2016.
 */
public class RectangularBounds extends Bounds {

    public float width;
    public float height;

    public RectangularBounds(float x,float y,float width,float height){
        center = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }

    public RectangularBounds(Vector2 center,float width,float height){
        this(center.x,center.y,width,height);
    }

    public RectangularBounds(RectangularBounds rectangularBounds){
        this(rectangularBounds.center.x,rectangularBounds.center.y,rectangularBounds.width,rectangularBounds.height);
    }

    @Override
    public boolean isPointWithin(float x, float y) {
        boolean fitsX = Math.abs(x - center.x) < width/2;
        boolean fitsY = Math.abs(y - center.y) < height/2;
        return fitsX && fitsY;
    }

    @Override
    public boolean isPointWithin(Vector2 vector2) {
        return isPointWithin(vector2.x,vector2.y);
    }




}
