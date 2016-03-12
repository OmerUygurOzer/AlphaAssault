package resources;

/**
 * Created by Omer on 3/9/2016.
 */
public interface ResourceUser {
    String getName();
    UsedResources getUsedResources();
    void insertResources(UsedResources usedResources);
    void registerForTextureRegion(String key);
    void registerForSound(String key);
    void registerForMusic(String key);
}
