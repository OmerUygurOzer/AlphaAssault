package ingame.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import graphics.FrameAnimator;
import ingame.World;

/**
 * Created by Omer on 2/9/2016.
 */
public abstract class Tile extends Entity {


    protected Tile(World world) {
        super(world);
    }

    public Tile(GameObject gameObject) {
        super(gameObject);
    }
}
