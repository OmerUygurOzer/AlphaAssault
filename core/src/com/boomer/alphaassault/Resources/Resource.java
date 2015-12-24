package com.boomer.alphaassault.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.graphicsutils.TextureSplitter;

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

    public static final int GAME_ALL = 1;
/*
* OTHER TEXTURES
*
*
*
*
* */
    //GUI
    public static final int GUI_ALL = 2;
    public static final int ANALOG = 3;
    public static final int GAME_FRAME = 4;
    public static final int FONTS = 5;
    public static final int BUTTONS = 6;

    //IN-GAME !
    public static final int IN_GAME = 20;
    public static final int BACKGROUND = 21;
    public static final int TREES = 22;
    public static final int CRATES = 23;
    public static final int BUSHES = 24;
    public static final int ROCKS = 25;
    public static final int WATER = 26;



    //ICONS
    public static final int TEXTURE_PLAYER = 60;
    public static final int ICONS = 61;
    //public static final int RUN = 61;
    //public static final int FIRE = 62;
    //public static final int FLASHBANG = 63;

    //IN-GAME
    public static final int BULLET = 80;
    public static final int FLASHBANG = 81;

    //VISUALS
    public static final int SMOKE = 100;


    public Resource() {
        textures = new HashMap<Integer, Texture>();
        textureRegions = new HashMap<Integer, TextureRegion[][]>();
        musics = new HashMap<Integer, Music>();
        sounds = new HashMap<Integer, Sound>();

    }

    public void initialize(){
        loadTexture(TEXTURE_REGION_ASSAULT_TROOPER,"character.png");
        loadTexture(TEXTURE_PLAYER,"pcprincipal.png");
        loadTexture(GUI_ALL,"GUI/gui.png");
        loadTexture(IN_GAME,"game/ingame.png");
        loadTexture(GAME_ALL,"game/game.png");



        loadTextureRegion(ANALOG,getTexture(GUI_ALL),512,128,128,128,4,1);
        loadTextureRegion(GAME_FRAME,getTexture(GUI_ALL),0,258,128,128,1,1);
        loadTextureRegion(FONTS,getTexture(GUI_ALL),0,0,32,43,16,6);
        loadTextureRegion(BUTTONS,getTexture(GUI_ALL),512,0,256,128,2,1);

        //MAP
        loadTextureRegion(BACKGROUND,getTexture(IN_GAME),160,0,80,80,2,1);
        loadTextureRegion(TREES,getTexture(IN_GAME),0,0,40,80,4,1);
        loadTextureRegion(ROCKS,getTexture(IN_GAME),0,80,40,40,4,1);
        loadTextureRegion(CRATES,getTexture(IN_GAME),0,120,40,40,4,1);
        loadTextureRegion(WATER,getTexture(IN_GAME),0,160,40,40,4,1);
        loadTextureRegion(BUSHES,getTexture(IN_GAME),0,200,20,20,4,1);

        //ICONS
        loadTextureRegion(ICONS,getTexture(GAME_ALL),0,0,60,40,7,1);

        //GAME
        loadTextureRegion(BULLET,getTexture(IN_GAME),0,220,20,20,1,8);
        loadTextureRegion(FLASHBANG,getTexture(IN_GAME),20,220,20,20,1,1);

        //VISUALS
        loadTextureRegion(SMOKE,getTexture(IN_GAME),320,0,80,80,7,1);
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

    public void loadTextureRegion(int _key,Texture _texture,int _x,int _y,int _width,int _height,int _xNumber,int _yNumber){
        textureRegions.put(_key, TextureSplitter.split(_texture,_x,_y,_width,_height,_xNumber,_yNumber));
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
