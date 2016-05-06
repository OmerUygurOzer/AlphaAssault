package ingame.objects;

import exceptions.GameEngineException;
import ingame.World;
import ingame.logic.AttributeHolder;
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
        this.objectState.addAttribute("isTile",true);
        this.objectState.addAttribute("isInstantiated",false);
    }



    @Override
    public String getName() {
        return objectState.getText("name");
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
    public void newInstance(World world) {
        this.world = world;
        this.id    = world.getNewID();
        this.objectState.replaceAttribute("isInstantiated",true);

    }

    @Override
    public void loadInstance(World world,int id) {

    }

    @Override
    public boolean isInstantiated() {
        return objectState.getBinary("isInstantiated");
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
        return (int)objectState.getNumeric("id");
    }
}
