package level;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/17/2016.
 */
public abstract class Tile {

    protected List<TileCrossing> crossings = new ArrayList<TileCrossing>();

    protected Vector2 position;

    public Tile(Vector2 position){
        this.position = position;
    }

    public void addCrossing(TileCrossing crossing){
        crossings.add(crossing);
    }

}
