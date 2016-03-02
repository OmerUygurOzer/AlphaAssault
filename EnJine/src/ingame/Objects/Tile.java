package ingame.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import graphics.FrameAnimator;

/**
 * Created by Omer on 2/9/2016.
 */
public abstract class Tile extends GameObject {
    public int width;
    public int height;
    public Polygon bounds;
    protected FrameAnimator frameAnimator;
    public Sprite currentFrame;


    protected abstract void createBounds();
    public abstract void draw(SpriteBatch spriteBatch);

}
