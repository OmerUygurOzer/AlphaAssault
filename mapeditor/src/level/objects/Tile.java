package level.objects;

import com.badlogic.gdx.math.Vector2;
import level.utils.Updateable;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 1/17/2016.
 */
public abstract class Tile implements Updateable {

    protected List<BufferedImage> frames = new ArrayList<BufferedImage>();

    protected Vector2 position;

    public Tile() {
        super();
        position = new Vector2();
    }

    public void setPosition(Vector2 position){this.position = position;}

    public void setPosition(float x, float y){this.setPosition(new Vector2(x,y));}

    public void addFrame(BufferedImage frame){frames.add(frame);}

}
