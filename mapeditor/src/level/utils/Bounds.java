package level.utils;

import com.badlogic.gdx.math.Vector2;


/**
 * Created by Omer on 1/17/2016.
 */
public abstract class Bounds {
    protected Vector2 center;

    public int width;
    public int heigth;

    public Bounds(Vector2 center,int width, int heigth) {
        this.center = center;
        this.width = width;
        this.heigth = heigth;
    }

    public abstract boolean isWithin(Vector2 point);
}
