package ingame.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import exceptions.GameEngineException;
import graphics.FrameAnimator;
import ingame.World;
import ingame.logic.AttributeHolder;
import ingame.logic.Attributes;
import resources.ResourceManager;
import resources.ResourceUser;
import resources.UsedResources;

/**
 * Created by Omer on 2/9/2016.
 */
public class Tile extends Entity  {

    private UsedResources usedResources;

    private World world;

    public Tile() {
        super();
        this.usedResources = new UsedResources();
        this.attributes.addAttribute("isTile",true);
        this.attributes.addAttribute("isInstantiated",false);
    }



    @Override
    public String getName() {
        return attributes.getText("name");
    }

    @Override
    public UsedResources getUsedResources() {
        return usedResources;
    }

    @Override
    public void insertResources(UsedResources usedResources) {
        this.usedResources = usedResources;
    }

    @Override
    public void registerForTextureRegion(String key) {
        usedResources.atlasRegions.add(key);
    }

    @Override
    public void registerForSound(String key) {
        usedResources.sounds.add(key);
    }

    @Override
    public void registerForMusic(String key) {
        usedResources.musics.add(key);
    }

    @Override
    public void instantiate(World world, EntityModel model, AttributeHolder instanceData) {
        this.world = world;
        this.attributes.addAttribute("id",world.getNewID());
        this.attributes.replaceAttribute("isInstantiated",true);
        this.attributes.insert(model.modelAttributes);
        this.usedResources = model.usedResources;


    }

    @Override
    public boolean isInstantiated() {
        return attributes.getBinary("isInstantiated");
    }

    @Override
    public World getWorld() {
        if(this.isInstantiated()){
            throw new GameEngineException("WorldObject: has not been initialized.");
        }
        return world;
    }

    @Override
    public int getID() {
        if(this.isInstantiated()){
            throw new GameEngineException("WorldObject: has not been initialized.");
        }
        return (int)attributes.getNumeric("id");
    }
}
