package com.boomer.alphaassault.graphics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.utilities.Location;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{

    public static final int STATE_BEING_RENDERED = 0;
    public static final int STATE_BEING_UPDATED = 1;
    public static final int STATE_RECENTLY_UPDATED = 2;

    public int CURRENT_STATE;

    private ConcurrentHashMap<Long,Location> locations;
    private ConcurrentHashMap<Long,Sprite> sprites;
    private ConcurrentHashMap<Integer,OrthographicCamera> cameras;
    private ConcurrentHashMap<Integer,List<Long>> cameraMapping;






    public RenderState(){
        locations = new ConcurrentHashMap<Long, Location>();
        sprites = new ConcurrentHashMap<Long, Sprite>();
        cameras = new ConcurrentHashMap<Integer, OrthographicCamera>();
        cameraMapping = new ConcurrentHashMap<Integer, List<Long>>();

    }

    public void addCamera(int _cameraType,OrthographicCamera _camera){
        cameras.put(_cameraType,_camera);
    }

    public void add(int _cameraType,long _referenceID,Sprite _sprite,Location _location){
        locations.put(_referenceID,_location);
        sprites.put(_referenceID,_sprite);
        if(!cameraMapping.containsKey(_cameraType)){
            List<Long> list = new ArrayList<Long>();
            list.add(_referenceID);
            cameraMapping.put(_cameraType,list);

        }else{
            cameraMapping.get(_cameraType).add(_referenceID);
        }



    }

    public void remove(long _referenceID){

            locations.remove(_referenceID);
            sprites.remove(_referenceID);



    }

    public void render(SpriteBatch _spriteBatch) throws InterruptedException {

        synchronized (this) {

            for (int key : cameras.keySet()) {
                _spriteBatch.setProjectionMatrix(cameras.get(key).combined);
                _spriteBatch.begin();
                if (cameraMapping.get(key) == null) {
                    _spriteBatch.end();
                    continue;
                }
                for (long MAPPER : cameraMapping.get(key)) {
                    sprites.get(MAPPER).draw(_spriteBatch);

                }
                _spriteBatch.end();
            }

        }

    }
     public void copy(RenderState _renderState) {
         synchronized (this){
         sprites.clear();
         locations.clear();
         cameras.clear();
         cameraMapping.clear();
         sprites.putAll(_renderState.getSprites());
         locations.putAll(_renderState.getLocations());
         cameras.putAll(_renderState.getCameras());
         /*
             for (int key : _renderState.getCameras().keySet()) {
             if (cameraMapping.get(key) == null) {
                 continue;
             }
             for (long MAPPER : cameraMapping.get(key)) {


             }

         }*/
         cameraMapping.putAll(_renderState.getCameraMapping());


         CURRENT_STATE = _renderState.CURRENT_STATE;
     }
     }

    private ConcurrentHashMap<Long,Sprite> getSprites(){
        return sprites;
    }

    private ConcurrentHashMap<Long,Location> getLocations(){ return locations;
    }

    private ConcurrentHashMap<Integer,OrthographicCamera> getCameras (){return cameras;}

    private ConcurrentHashMap<Integer,List<Long>> getCameraMapping(){return cameraMapping;}



    public boolean isEmpty(){
        return sprites.isEmpty();
    }

    public int getCurrentState(){
        return CURRENT_STATE;
    }

}
