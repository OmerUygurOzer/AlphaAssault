package level.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 1/17/2016.
 */
public class BoundsIsomorphic extends Bounds {


    public BoundsIsomorphic(Vector2 center, int width, int heigth) {
        super(center, width, heigth);
    }

    @Override
    public boolean isWithin(Vector2 point) {
        return false;
    }
}
