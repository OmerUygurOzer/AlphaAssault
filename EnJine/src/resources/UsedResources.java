package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 3/10/2016.
 */
public class UsedResources implements Serializable {
    public List<String> atlasRegions = new ArrayList<String>();
    public List<String> sounds       = new ArrayList<String>();
    public List<String> musics        = new ArrayList<String>();

    public UsedResources getNew(){
        UsedResources usedResources = new UsedResources();
        usedResources.atlasRegions.addAll(atlasRegions);
        usedResources.sounds.addAll(sounds);
        usedResources.musics.addAll(musics);
        return usedResources;
    }

    public enum Type{
        REGION,
        SOUND,
        MUSIC;
    }
}
