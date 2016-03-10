package resources;

/**
 * Created by Omer on 3/9/2016.
 */
public interface ResourceUser {
    void registerForTextureRegion(ResourceManager resourceManager,String key,String name);
    void registerForSound(ResourceManager resourceManager,String key,String name);
    void registerForMusic(ResourceManager resourceManager,String key,String name);
}
