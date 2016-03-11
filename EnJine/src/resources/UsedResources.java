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
    public List<String> music        = new ArrayList<String>();

    public enum Type{
        REGION,
        SOUND,
        MUSIC;
    }
}
