package resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import exceptions.GameEngineException;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Omer on 3/9/2016.
 */
public class ResourcePacker implements Packer,Serializable {


    private transient String rawDir;
    private transient String packedDir;
    private String atlasDir;

    private Map<String,String> resources;

    private Map<String,String> textureFiles;

    private Map<String,String> soundFiles;
    private Map<String,String> musicFiles;


    private transient TextureAtlas atlas;
    private String atlasName;

    private transient Map<String,Sound> soundResources;
    private transient Map<String,Music> musicResources;

    private transient boolean isPacked = false;
    private transient boolean isUnpacked = false;
    private transient boolean disposed = false;



    public ResourcePacker(){
        this.resources     = new HashMap<String, String>();
        this.textureFiles  = new HashMap<String, String>();
        this.soundFiles    = new HashMap<String, String>();
        this.musicFiles    = new HashMap<String, String>();

        this.soundResources= new HashMap<String, Sound>();
        this.musicResources= new HashMap<String, Music>();
    }

    public void setDirectoryNames(String rawDir,String packedDir,String atlasName){
        this.rawDir = Gdx.files.getLocalStoragePath()+ "\\" + rawDir;
        this.packedDir = Gdx.files.getLocalStoragePath()+ "\\" + packedDir;

        this.atlasName= atlasName + ".atlas";
        this.atlasDir = packedDir + "\\" + atlasName;
    }

    public void addTexture(String key,String fileName){
        keyCheck(key);
        resources.put(key,fileName);
        textureFiles.put(key,fileName);
    }

    public void addSound(String key,String fileName){
        keyCheck(key);
        resources.put(key,fileName);
        soundFiles.put(key,fileName);
    }

    public void addMusic(String key,String fileName){
        keyCheck(key);
        resources.put(key,fileName);
        musicFiles.put(key,fileName);
    }




    public TextureRegion getTextureRegion(String key){
        unPackCheck();
        return atlas.findRegion(key);
    }

    public Sound getSound(String key){
        unPackCheck();
        if(soundResources.containsKey(key))return soundResources.get(key);
        return null;
    }

    public Music getMusic(String key){
        unPackCheck();
        if(musicResources.containsKey(key))return musicResources.get(key);
        return null;
    }



    private void keyCheck(String key){
        if(resources.containsKey(key)){throw new GameEngineException("ResourcePacker: Two resources can not have the same key.");}
    }

    private void unPackCheck(){
        if(!isUnpacked){throw new GameEngineException("Resources are not unpacked.");}
    }

    @Override
    public void pack() {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.minWidth = 1024;
        settings.maxWidth = 2048;
        TexturePacker.process(settings,rawDir,packedDir,atlasName);
        isPacked = true;
    }

    @Override
    public void unpack() {
        atlas = new TextureAtlas(Gdx.files.absolute(atlasDir));

        for(String key:soundFiles.keySet()){
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundFiles.get(key)));
            soundResources.put(key,sound);
        }

        for(String key:musicFiles.keySet()){
            Music music = Gdx.audio.newMusic(Gdx.files.internal(musicFiles.get(key)));
            musicResources.put(key,music);
        }

        isUnpacked = true;
    }

    @Override
    public ResourceData getPackedData() {
        unPackCheck();
        ResourceData resourceData = new ResourceData();
        resourceData.atlas = atlas;
        resourceData.soundResources = soundResources;
        resourceData.musicResources = musicResources;
        return resourceData;
    }

    @Override
    public void dispose() {
        atlas.dispose();

        for(String key : soundResources.keySet()){
            soundResources.get(key).dispose();
        }

        for(String key : musicResources.keySet()){
            musicResources.get(key).dispose();
        }

        disposed = true;
    }


}
