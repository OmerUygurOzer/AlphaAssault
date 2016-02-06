package maths.geometry;

import com.badlogic.gdx.math.Vector2;


/**
 * Created by Omer on 2/5/2016.
 */
public abstract class Bounds {
    public Vector2 center;
    public abstract boolean isPointWithin(float x,float y);
    public abstract boolean isPointWithin(Vector2 vector2);

}
