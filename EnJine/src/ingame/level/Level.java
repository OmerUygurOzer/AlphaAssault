package ingame.level;


import com.badlogic.gdx.Gdx;
import resources.ResourcePacker;

/**
 * Created by Omer on 2/5/2016.
 */
public class Level  {

    public String name;

    private ResourcePacker resourcePacker;

    public int width;
    public int height;

    public int tileSize;


    public Level(){
        this.resourcePacker = new ResourcePacker("","","");
    }





}
