package com.boomer.alphaassault.graphics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{

    public static final int STATE_BEING_RENDERED = 0;
    public static final int STATE_BEING_UPDATED = 1;
    public static final int STATE_RECENTLY_UPDATED = 2;

    public int CURRENT_STATE;

    private HashMap<Long,Sprite> sprites;
    private HashMap<Integer,OrthographicCamera> cameras;
    private HashMap<Integer,List<Long>> cameraMapping;

    public RenderState(){
        sprites = new HashMap<Long, Sprite>();
        cameras = new HashMap<Integer, OrthographicCamera>();
        cameraMapping = new HashMap<Integer, List<Long>>();

    }

    public void addCamera(int _cameraType,OrthographicCamera _camera){
        cameras.put(_cameraType,_camera);
    }

    public void add(int _cameraType,long _referenceID,Sprite _sprite){
        sprites.put(_referenceID,new Sprite(_sprite));
        if(!cameraMapping.containsKey(_cameraType)){
            List<Long> list = new ArrayList<Long>();
            list.add(_referenceID);
            cameraMapping.put(_cameraType,list);

        }else{
            cameraMapping.get(_cameraType).add(_referenceID);
        }



    }

    public void remove(long _referenceID){
            sprites.remove(_referenceID);
    }

    public void  render(SpriteBatch _spriteBatch) {
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
     public void getUpdates(RenderState _renderState) {
         synchronized (this){
            for(Long key : _renderState.getSprites().keySet()){
                if(!sprites.containsKey(key)){
                    sprites.put(key,new Sprite(_renderState.getSprites().get(key)));
                }else {
                    sprites.get(key).set(_renderState.getSprites().get(key));
                }
            }
             for(Long key : sprites.keySet()){
                 if(!_renderState.getSprites().containsKey(key)){
                     sprites.remove(key);

                 }
             }

             cameraMapping.clear();
             for(Integer map : _renderState.getCameraMapping().keySet()){
                 cameraMapping.put(map,new ArrayList<Long>(_renderState.getCameraMapping().get(map)));
             }

     }
     }

    public void updateElement(long _referenceID,Sprite _sprite){
            synchronized (this){
                sprites.get(_referenceID).set(_sprite);
            }
    }

    public void set(RenderState _renderState){
        sprites.clear();
        cameras.clear();
        cameraMapping.clear();
        for(Long key : _renderState.getSprites().keySet()){
                sprites.put(key,new Sprite(_renderState.getSprites().get(key)));
        }

       for(Integer map : _renderState.getCameraMapping().keySet()){
           cameraMapping.put(map,new ArrayList<Long>(_renderState.getCameraMapping().get(map)));
       }
        cameras.putAll(_renderState.getCameras());
        CURRENT_STATE = _renderState.getCurrentState();

    }



    private HashMap<Long,Sprite> getSprites(){return sprites;}
    private HashMap<Integer,OrthographicCamera> getCameras (){return cameras;}
    private HashMap<Integer,List<Long>> getCameraMapping(){return cameraMapping;}
    public boolean isEmpty(){return sprites.isEmpty();}
    public int getCurrentState(){return CURRENT_STATE;}
    public void setCurrentState(int _state){CURRENT_STATE  = _state;}


}
