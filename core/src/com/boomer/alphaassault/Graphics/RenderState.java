package com.boomer.alphaassault.graphics;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;



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
        public int tracker;
        public int viewType;
        public long referenceId;
        public int depth;
        public Sprite sprite;

        public Addition(int _viewType,long _referenceId,int _depth,Sprite _sprite){
            viewType = _viewType;
            referenceId = _referenceId;
            depth = _depth;
            sprite = new Sprite(_sprite);
            tracker = 1;
        }
    }

    private class Removal{
        public int tracker;
        public long referenceId;
        public int depth;

        public Removal(long _referenceId,int _depth){
            referenceId = _referenceId;
            depth = _depth;
            tracker = 1;
        }
    }

    private List<Addition> additions;
    private List<Removal> removals;

    private Map<Integer,Map<Long,Sprite>> sprites;
    private Map<Integer,Map<Integer,Viewport>> viewPorts;
    private Map<Integer,Map<Integer,List<Long>>> cameraMapping; //MAP OF CAMERAS FOR EACH DEPTH AND REFERENCE IDS FOR OBJECTS NEED TO BE DRAWN USING THOSE CAMERAS

    public RenderState(){
        sprites = new ConcurrentHashMap<Integer, Map<Long, Sprite>>();
        viewPorts = new ConcurrentHashMap<Integer, Map<Integer, Viewport>>();
        cameraMapping = new ConcurrentHashMap<Integer, Map<Integer, List<Long>>>();

        additions = new CopyOnWriteArrayList<Addition>();
        removals = new CopyOnWriteArrayList<Removal>();

        for(int depth = 0;depth<4;depth++){
            sprites.put(depth,new ConcurrentHashMap<Long, Sprite>());
            viewPorts.put(depth,new ConcurrentHashMap<Integer, Viewport>());
            cameraMapping.put(depth,new ConcurrentHashMap<Integer, List<Long>>());
        }


    }

    public void addView(int _viewType, Viewport _viewPort){
        for(int depth: viewPorts.keySet()) {
            viewPorts.get(depth).put(_viewType, _viewPort);
        }
    }

    public void addElement(int _cameraType, long _referenceId,int _depth,Sprite _sprite){
        additions.add(new Addition(_cameraType,_referenceId,_depth,_sprite));
        sprites.get(_depth).put(_referenceId,new Sprite(_sprite));
        if(!cameraMapping.get(_depth).containsKey(_cameraType)){
            List<Long> list = new CopyOnWriteArrayList<Long>();
            list.add(_referenceId);
            cameraMapping.get(_depth).put(_cameraType,list);

        }else{
            cameraMapping.get(_depth).get(_cameraType).add(_referenceId);
        }
    }

    public void removeElement(long _referenceId,int _depth){
        removals.add(new Removal(_referenceId,_depth));
       sprites.get(_depth).remove(_referenceId);
        for(int map: cameraMapping.get(_depth).keySet()){
            if(cameraMapping.get(_depth).get(map).contains(_referenceId)){
                cameraMapping.get(_depth).get(map).remove(_referenceId);
            }
        }
    }
    public void updateElement(long _referenceId,int _depth,Sprite _sprite){
       // if(sprites.get(_depth).get(_referenceId)!=null) {
            sprites.get(_depth).get(_referenceId).set(_sprite);
        //}
    }


    //DEPTH BASE IS DRAWN FIRST
    //GAME SCREEN IS DRAWN LAST
    public void  render(SpriteBatch _spriteBatch) {
        for(int depth: sprites.keySet()) {
            for (int key : viewPorts.get(depth).keySet()) {
                viewPorts.get(depth).get(key).apply();
                _spriteBatch.setProjectionMatrix(viewPorts.get(depth).get(key).getCamera().combined);
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
         for(Addition incomingAddition:_renderState.getAdditions()){
             sprites.get(incomingAddition.depth).put(incomingAddition.referenceId,incomingAddition.sprite);
             if(!cameraMapping.get(incomingAddition.depth).containsKey(incomingAddition.viewType)){
                 List<Long> list = new CopyOnWriteArrayList<Long>();
                 list.add(incomingAddition.referenceId);
                 cameraMapping.get(incomingAddition.depth).put(incomingAddition.viewType,list);

             }else{
                 cameraMapping.get(incomingAddition.depth).get(incomingAddition.viewType).add(incomingAddition.referenceId);
             }
             if(incomingAddition.tracker>0){
                 Addition passingAddition = new Addition(incomingAddition.viewType,incomingAddition.referenceId,incomingAddition.depth,new Sprite(incomingAddition.sprite));
                 passingAddition.tracker = incomingAddition.tracker-1;
                 additions.add(passingAddition);

             }
         }

         for(Removal incomingRemoval:_renderState.getRemovals()){
             sprites.get(incomingRemoval.depth).remove(incomingRemoval.referenceId);
             for(int map: cameraMapping.get(incomingRemoval.depth).keySet()){
                 if(cameraMapping.get(incomingRemoval.depth).get(map).contains(incomingRemoval.referenceId)){
                     cameraMapping.get(incomingRemoval.depth).get(map).remove(incomingRemoval.referenceId);
                 }
             }
             if(incomingRemoval.tracker>0){
                 Removal passingRemoval = new Removal(incomingRemoval.referenceId,incomingRemoval.depth);
                 passingRemoval.tracker = passingRemoval.tracker-1;
                 removals.add(passingRemoval);

             }

         }

     }

    public void set(RenderState _renderState){
        for(int depth: sprites.keySet()) {
            sprites.get(depth).clear();
            viewPorts.get(depth).clear();
            cameraMapping.get(depth).clear();
            for (Long key : _renderState.getSprites().get(depth).keySet()) {
                sprites.get(depth).put(key, new Sprite(_renderState.getSprites().get(depth).get(key)));
            }

            for (Integer map : _renderState.getCameraMapping().get(depth).keySet()) {
                cameraMapping.get(depth).put(map, new ArrayList<Long>(_renderState.getCameraMapping().get(depth).get(map)));
            }
            viewPorts.get(depth).putAll(_renderState.getViewPorts().get(depth));

        }
        CURRENT_STATE = _renderState.getCurrentState();
    }



    private Map<Integer,Map<Long,Sprite>> getSprites(){return sprites;}
    private Map<Integer,Map<Integer,Viewport>> getViewPorts(){return viewPorts;}
    private Map<Integer,Map<Integer,List<Long>>> getCameraMapping(){return cameraMapping;}
    public int getCurrentState(){return CURRENT_STATE;}
    public void setCurrentState(int _state){CURRENT_STATE  = _state;}
    public List<Addition> getAdditions(){
        List<Addition> list = new CopyOnWriteArrayList<Addition>();
        list.addAll(additions);
        additions.clear();
        return list;
    }
    public List<Removal> getRemovals(){
        List<Removal> list = new CopyOnWriteArrayList<Removal>();
        list.addAll(removals);
       removals.clear();
        return list;
    }


}
