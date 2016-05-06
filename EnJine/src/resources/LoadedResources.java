package resources;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 5/5/2016.
 */
public class LoadedResources {
    public Map<String,TextureRegion> regions = new HashMap<String, TextureRegion>();
    public Map<String,Music>         musics  = new HashMap<String, Music>();
    public Map<String,Sound>         sounds  = new HashMap<String, Sound>();
}
