package level.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 1/17/2016.
 */
public class Bounds2D extends Bounds {

    public Bounds2D(Vector2 center,int width,int height) {
        super(center,width,height);
    }

    @Override
    public boolean isWithin(Vector2 point){
        boolean fitsX = Math.abs(point.x - center.x) < width/2;
        boolean fitsY = Math.abs(point.y - center.y) < heigth/2;
        return fitsX && fitsY;
    }
}
