package com.boomer.alphaassault.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

/**
 * Created by Omer on 11/25/2015.
 */
public class Resource {
    private static HashMap<Integer,Texture> textures;
    private static HashMap<Integer,Sound> sounds;
    private static HashMap<Integer,Music> musics;


    public static final int TEXTURE_ASSAULT_TROOPER = 0;
/*
* OTHER TEXTURES
*
*
*
*
* */
    public static final int TEXTURE_LEFT_BUTTON = 12;
    public static final int TEXTURE_LEFT_CIRCLE = 13;
//OTHER TEXTURES

    public static final int TEXTURE_HUD_CAM = 20;


    public Resource() {
        textures = new HashMap<Integer, Texture>();
        musics = new HashMap<Integer, Music>();
        sounds = new HashMap<Integer, Sound>();

    }

    public void initialize(){
        Texture texture = new Texture(Gdx.files.internal("character.png"));
        textures.put(TEXTURE_ASSAULT_TROOPER,texture);
        texture = new Texture(Gdx.files.internal("greenbutton.png"));
        textures.put(TEXTURE_LEFT_BUTTON,texture);
        texture = new Texture(Gdx.files.internal("greencircle.png"));
        textures.put(TEXTURE_LEFT_CIRCLE,texture);
        texture = new Texture(Gdx.files.internal("hudcam.png"));
        textures.put(TEXTURE_HUD_CAM,texture);

    }

    //TEXTURE HANDLERS
    public void loadTexture(int _key, String _path){
        Texture texture = new Texture(Gdx.files.internal(_path));
        textures.put(_key,texture);
    }

    public static Texture getTexture(int _key){
        return textures.get(_key);
    }

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
            Texture tex = (Texture) o;
            tex.dispose();
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
