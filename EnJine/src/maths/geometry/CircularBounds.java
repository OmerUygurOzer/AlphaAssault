package maths.geometry;

import com.badlogic.gdx.math.Vector2;
import maths.MathUtilities;

/**
 * Created by Omer on 2/5/2016.
 */
public class CircularBounds extends Bounds {

    public float radius;

    public CircularBounds(float x,float y,float radius){
        this.radius = radius;
        this.center = new Vector2(x,y);
    }

    public CircularBounds(Vector2 center,float radius){
        this(center.x,center.y,radius);
    }
    @Override
    public boolean isPointWithin(float x, float y) {
        return Vector2.dst(x,y,center.x,center.y)<=radius;
    }

    @Override
    public boolean isPointWithin(Vector2 vector2) {
        return isPointWithin(vector2.x,vector2.y);
    }


}
