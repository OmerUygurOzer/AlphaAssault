package com.boomer.alphaassault.graphics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.utilities.Location;



import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{

    public static final int STATE_BEING_RENDERED = 0;
    public static final int STATE_BEING_UPDATED = 1;
    public static final int STATE_RECENTLY_UPDATED = 2;

    public int CURRENT_STATE;

    private ConcurrentHashMap<Long,Location> SCREEN_CAM_locations;
    private ConcurrentHashMap<Long,Sprite> SCREEN_CAM_sprites;

    private ConcurrentHashMap<Long,Location> MAP_CAM_locations;
    private ConcurrentHashMap<Long,Sprite> MAP_CAM_sprites;



    public RenderState(){
        SCREEN_CAM_locations = new ConcurrentHashMap<Long, Location>();
        SCREEN_CAM_sprites = new ConcurrentHashMap<Long, Sprite>();

        MAP_CAM_locations = new ConcurrentHashMap<Long, Location>();
        MAP_CAM_sprites = new ConcurrentHashMap<Long, Sprite>();
    }

    public void add(int _cameraType,long _referenceID,Sprite _sprite,Location _location){
        if(_cameraType == GameGraphics.CAMERA_TYPE_SCREEN){
            SCREEN_CAM_locations.put(_referenceID,_location);
            SCREEN_CAM_sprites.put(_referenceID,_sprite);
        }
        if(_cameraType == GameGraphics.CAMERA_TYPE_MAP){
            MAP_CAM_locations.put(_referenceID,_location);
            MAP_CAM_sprites.put(_referenceID,_sprite);
        }



    }

    public void remove(long _referenceID){
        if(SCREEN_CAM_locations.contains(_referenceID)){
            SCREEN_CAM_locations.remove(_referenceID);
            SCREEN_CAM_sprites.remove(_referenceID);
        }
        if(MAP_CAM_locations.contains(_referenceID)){
            MAP_CAM_locations.remove(_referenceID);
            MAP_CAM_sprites.remove(_referenceID);
        }


    }

    public void render() {
        GameGraphics.MAIN_SPRITE_BATCH.setProjectionMatrix(GameGraphics.SCREEN_CAM.combined);
        GameGraphics.MAIN_SPRITE_BATCH.begin();
        for (Sprite CURR : SCREEN_CAM_sprites.values()) {
            CURR.draw(GameGraphics.MAIN_SPRITE_BATCH);

        }
        GameGraphics.MAIN_SPRITE_BATCH.end();

        GameGraphics.MAIN_SPRITE_BATCH.setProjectionMatrix(GameGraphics.MAP_CAM.combined);
        GameGraphics.MAIN_SPRITE_BATCH.begin();
        for (Sprite CURR : MAP_CAM_sprites.values()) {
            CURR.draw(GameGraphics.MAIN_SPRITE_BATCH);

        }

        GameGraphics.MAIN_SPRITE_BATCH.end();

    }
     public void copy(RenderState _renderState){
         MAP_CAM_locations.clear();
         MAP_CAM_locations.clear();
         SCREEN_CAM_locations.clear();
         SCREEN_CAM_sprites.clear();
         MAP_CAM_sprites.putAll(_renderState.getMAPSprites());
         SCREEN_CAM_sprites.putAll(_renderState.getScreenSprites());
         MAP_CAM_locations.putAll(_renderState.getMAPLocations());
         SCREEN_CAM_locations.putAll(_renderState.getScreenLocations());

         CURRENT_STATE = _renderState.CURRENT_STATE;

     }

    private ConcurrentHashMap<Long,Sprite> getMAPSprites(){
        return MAP_CAM_sprites;
    }

    private ConcurrentHashMap<Long,Location> getMAPLocations(){ return MAP_CAM_locations;
    }

    private ConcurrentHashMap<Long,Sprite> getScreenSprites(){
        return SCREEN_CAM_sprites;
    }

    private ConcurrentHashMap<Long,Location> getScreenLocations(){
        return SCREEN_CAM_locations;
    }

    public boolean isEmpty(){
        return MAP_CAM_sprites.isEmpty() && SCREEN_CAM_sprites.isEmpty();
    }

    public int getCurrentState(){
        return CURRENT_STATE;
    }

   // public void info(){
     //   System.out.println("RENDERING: "+ sprites.size());
   // }


}
