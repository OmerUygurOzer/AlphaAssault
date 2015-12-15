package com.boomer.alphaassault.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Omer on 11/25/2015.
 */
public class Resource {
    private static HashMap<Integer,Texture> textures;
    private static HashMap<Integer,Sound> sounds;
    private static HashMap<Integer,Music> musics;
    private static HashMap<Integer,TextureRegion[][]> textureRegions;


    public static final int TEXTURE_REGION_ASSAULT_TROOPER = 0;
/*
* OTHER TEXTURES
*
*
*
*
* */
    public static final int TEXTURE_LEFT_BUTTON = 12;
    public static final int TEXTURE_LEFT_CIRCLE = 13;
    public static final int TEXTURE_RIGHT_BUTTON = 14;
    public static final int TEXTURE_RIGHT_CIRCLE = 15;

    public static final int TEXTURE_BUTTON_BASE = 16;
    //OTHER TEXTURES

    public static final int TEXTURE_HUD_CAM = 20;

    //MAP TEXTURES

    public static final int TEXTURE_BACKGROUND = 40;
    public static final int TEXTURE_TREES  = 41;
    public static final int TEXTURE_OTHERS = 42;


    //ICONS
    public static final int TEXTURE_PLAYER = 60;
    public static final int TEXTURE_REGION_SKILL_ICONS = 61;


    //FONTS
    public static final int TEXTURE_FONTS = 70;

    public Resource() {
        textures = new HashMap<Integer, Texture>();
        textureRegions = new HashMap<Integer, TextureRegion[][]>();
        musics = new HashMap<Integer, Music>();
        sounds = new HashMap<Integer, Sound>();

    }

    public void initialize(){
        loadTexture(TEXTURE_BACKGROUND,"map/tiles_background.png");
        loadTexture(TEXTURE_REGION_ASSAULT_TROOPER,"character.png");
        loadTexture(TEXTURE_LEFT_BUTTON,"GUI/greenbutton.png");
        loadTexture(TEXTURE_LEFT_CIRCLE,"GUI/greencircle.png");
        loadTexture(TEXTURE_RIGHT_BUTTON,"GUI/redbutton.png");
        loadTexture(TEXTURE_RIGHT_CIRCLE,"GUI/redcircle.png");
        loadTexture(TEXTURE_HUD_CAM,"GUI/hudcam.png");
        loadTexture(TEXTURE_TREES,"map/trees.png");
        loadTexture(TEXTURE_OTHERS,"map/others.png");
        loadTexture(TEXTURE_BUTTON_BASE,"GUI/button.png");
        loadTexture(TEXTURE_FONTS,"GUI/fonts.gif");
        loadTexture(TEXTURE_PLAYER,"pcprincipal.png");
        loadTextureRegion(TEXTURE_REGION_SKILL_ICONS,"game/skillicons.png",50,50);
    }

    //TEXTURE HANDLERS
    public void loadTexture(int _key, String _path){
        Texture texture = new Texture(Gdx.files.internal(_path));
        textures.put(_key,texture);
    }

    public static Texture getTexture(int _key){
        return textures.get(_key);
    }

    public void loadTextureRegion(int _key,String _path,int _width,int _height){
        TextureRegion[][] splitRegions = TextureRegion.split(new Texture(Gdx.files.internal(_path)),_width,_height);
        textureRegions.put(_key,splitRegions);
    }

    //TEXTURE REGION HANDLERS
    public void removeTextureRegion(int _key){
        TextureRegion[][] splitRegions = textureRegions.get(_key);
        if(splitRegions !=null){
            textureRegions.remove(_key);
        }
    }

    public static TextureRegion[][] getTextureRegions(int _key){return textureRegions.get(_key);}

    public void removeTexture(int _key){
        Texture texture = textures.get(_key);
        if(texture != null){
            textures.remove(_key);
            texture.dispose();
        }
    }

    //SOUND HANDLERS
    public void loadSound(int _key, String _path){
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(_path));
        sounds.put(_key,sound);
    }

    public static Sound getSound(int _key){
        return sounds.get(_key);
    }

    public void removeSound(int _key){
        Sound sound = sounds.get(_key);
        if(sound!=null){
            sounds.remove(_key);
            sound.dispose();
        }
    }

    //MUSIC HANDLERS

    public void loadMusic(int _key,String _path){
        Music music = Gdx.audio.newMusic(Gdx.files.internal(_path));
        musics.put(_key,music);
    }

    public static Music getMusic(int _key){
        return musics.get(_key);
    }

    public void removeMusic(int _key){
        Music music = musics.get(_key);
        if(music!=null){
            musics.remove(_key);
            music.dispose();
        }

    }

    public void disposeAll(){
        for(Object o : textures.values()) {
            Texture texture = (Texture) o;
            texture.dispose();
        }
        textures.clear();
        for(Object o : musics.values()) {
            Music music = (Music) o;
            music.dispose();
        }
        musics.clear();
        for(Object o : sounds.values()) {
            Sound sound = (Sound) o;
            sound.dispose();
        }
        sounds.clear();
    }
}
