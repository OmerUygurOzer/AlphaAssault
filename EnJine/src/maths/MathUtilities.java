package maths;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 2/5/2016.
 */
public class MathUtilities {
    public static float square(float x){return x*x;}
    public static double pythagoras(float x, float y){return Math.sqrt(square(x)+square(y));}
    public static double pythagoras(Vector2 vec2){return pythagoras(vec2.x,vec2.y);}

}
