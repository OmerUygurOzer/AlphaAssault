package com.boomer.alphaassault.graphics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{

    public static final int STATE_BEING_RENDERED = 0;
    public static final int STATE_BEING_UPDATED = 1;
    public static final int STATE_RECENTLY_UPDATED = 2;

    public static final int DEPTH_BASE = 0;
    public static final int DEPTH_SURFACE = 1;
    public static final int DEPTH_AIR = 2;
    public static final int DEPTH_GAME_SCREEN = 3;

    public int CURRENT_STATE;

    private class Addition{
        public int cameraType;
        public long id;
        public int depth;
        public Sprite sprite;
        public Addition(int _cameraType,long _id,int _depth, Sprite _sprite){
            cameraType = _cameraType;
            id = _id;
            depth = _depth;
            sprite = new Sprite(_sprite);

        }
    }

    private class Removal{
        public long id;
        public int depth;
        public Removal(long _id, int _depth){
            id = _id;
            depth = _depth;

        }
    }

    private List<Addition> additions;
    private List<Removal> removals;


    private Map<Integer,Map<Long,Sprite>> sprites;
    private Map<Integer,Map<Integer,OrthographicCamera>> cameras;
    private Map<Integer,Map<Integer,List<Long>>> cameraMapping; //MAP OF CAMERAS FOR EACH DEPTH AND REFERENCE IDS FOR OBJECTS NEED TO BE DRAWN USING THOSE CAMERAS

    public RenderState(){
        sprites = new ConcurrentHashMap<Integer, Map<Long, Sprite>>();
        cameras = new ConcurrentHashMap<Integer, Map<Integer, OrthographicCamera>>();
        cameraMapping = new ConcurrentHashMap<Integer, Map<Integer, List<Long>>>();

        additions = new CopyOnWriteArrayList<Addition>();
        removals = new CopyOnWriteArrayList<Removal>();

        for(int depth = 0;depth<4;depth++){
            sprites.put(depth,new ConcurrentHashMap<Long, Sprite>());
            cameras.put(depth,new ConcurrentHashMap<Integer, OrthographicCamera>());
            cameraMapping.put(depth,new ConcurrentHashMap<Integer, List<Long>>());
        }


    }

    public void addCamera(int _cameraType,OrthographicCamera _camera){
        for(int depth:cameras.keySet()) {
            cameras.get(depth).put(_cameraType, _camera);
        }
    }

    public void addElement(int _cameraType, long _referenceID,int _depth,Sprite _sprite){
        additions.add(new Addition(_cameraType,_referenceID,_depth,_sprite));
        sprites.get(_depth).put(_referenceID,new Sprite(_sprite));
        if(!cameraMapping.get(_depth).containsKey(_cameraType)){
            List<Long> list = new CopyOnWriteArrayList<Long>();
            list.add(_referenceID);
            cameraMapping.get(_depth).put(_cameraType,list);

        }else{
            cameraMapping.get(_depth).get(_cameraType).add(_referenceID);
        }



    }

    public void removeElement(long _referenceID,int _depth){
        removals.add(new Removal(_referenceID,_depth));
       sprites.get(_depth).remove(_depth);
        for(int map: cameraMapping.get(_depth).keySet()){
            if(cameraMapping.get(_depth).get(map).contains(_referenceID)){
                cameraMapping.get(_depth).get(map).remove(_referenceID);
            }
        }
    }
    public void updateElement(long _referenceID,int _depth,Sprite _sprite){
        if(sprites.get(_depth).get(_referenceID)!=null) {
            sprites.get(_depth).get(_referenceID).set(_sprite);
        }
    }


    //DEPTH BASE IS DRAWN FIRST
    //GAME SCREEN IS DRAWN LAST
    public void  render(SpriteBatch _spriteBatch) {
        for(int depth: sprites.keySet()) {
            for (int key : cameras.get(depth).keySet()) {
                _spriteBatch.setProjectionMatrix(cameras.get(depth).get(key).combined);
                _spriteBatch.begin();
                if (cameraMapping.get(depth).get(key) == null) {
                    _spriteBatch.end();
                    continue;
                }
                for (long MAPPER : cameraMapping.get(depth).get(key)) {
                    sprites.get(depth).get(MAPPER).draw(_spriteBatch);
                }
                _spriteBatch.end();
            }
        }
    }

     public void getUpdates(RenderState _renderState) {
         for(Addition addition : _renderState.getAdditions()){
             sprites.get(addition.depth).put(addition.id,new Sprite(addition.sprite));
             if(!cameraMapping.get(addition.depth).containsKey(addition.cameraType)){
                 List<Long> list = new CopyOnWriteArrayList<Long>();
                 list.add(addition.id);
                 cameraMapping.get(addition.depth).put(addition.cameraType,list);

             }else{
                 cameraMapping.get(addition.depth).get(addition.cameraType).add(addition.id);
             }
         }
         for(Removal removal: _renderState.getRemovals()){
             removeElement(removal.id,removal.depth);
         }

     }

    public void set(RenderState _renderState){
        for(int depth: sprites.keySet()) {
            sprites.get(depth).clear();
            cameras.get(depth).clear();
            cameraMapping.get(depth).clear();
            for (Long key : _renderState.getSprites().get(depth).keySet()) {
                sprites.get(depth).put(key, new Sprite(_renderState.getSprites().get(depth).get(key)));
            }

            for (Integer map : _renderState.getCameraMapping().get(depth).keySet()) {
                cameraMapping.get(depth).put(map, new ArrayList<Long>(_renderState.getCameraMapping().get(depth).get(map)));
            }
            cameras.get(depth).putAll(_renderState.getCameras().get(depth));

        }
        CURRENT_STATE = _renderState.getCurrentState();
    }



    private Map<Integer,Map<Long,Sprite>> getSprites(){return sprites;}
    private Map<Integer,Map<Integer,OrthographicCamera>> getCameras (){return cameras;}
    private Map<Integer,Map<Integer,List<Long>>> getCameraMapping(){return cameraMapping;}
    public int getCurrentState(){return CURRENT_STATE;}
    public void setCurrentState(int _state){CURRENT_STATE  = _state;}
    public List<Addition> getAdditions(){
        List<Addition> list = new CopyOnWriteArrayList<Addition>();
        list.addAll(additions);
        additions.clear();
        return list;}
    public List<Removal> getRemovals(){
        List<Removal> list = new CopyOnWriteArrayList<Removal>();
        list.addAll(removals);
        removals.clear();
        return list;}


}
