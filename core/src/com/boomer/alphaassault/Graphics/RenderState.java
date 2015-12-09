package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.graphics.elements.BDrawable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{
/*
    private  class Addition{
        public int tracker;
        public int viewType;
        public long referenceId;
        public int depth;
        public BDrawable bDrawable;

        public Addition(int _viewType,long _referenceId,int _depth,BDrawable _bDrawable){
            viewType = _viewType;
            referenceId = _referenceId;
            depth = _depth;
            bDrawable = _bDrawable;
            tracker = 1;
        }
    }

    private  class Removal{
        public int tracker;
        public long referenceId;
        public int depth;

        public Removal(long _referenceId,int _depth){
            referenceId = _referenceId;
            depth = _depth;
            tracker = 1;
        }
    }

    private class Update{
        public long referenceId;
        public int depth;
        public BDrawable bDrawable;
        public int tracker;

        public Update(long _referenceId,int _depth,BDrawable _bDrawable){
            referenceId = _referenceId;
            depth = _depth;
            bDrawable = _bDrawable;
            //region = null;
            tracker = 1;
        }

    }
*/
    public static final int STATE_BEING_RENDERED = 0;
    public static final int STATE_BEING_UPDATED = 1;
    public static final int STATE_RECENTLY_UPDATED = 2;

    public static final int DEPTH_BASE = 0;
    public static final int DEPTH_SURFACE = 1;
    public static final int DEPTH_AIR = 2;
    public static final int DEPTH_GAME_SCREEN = 3;
    public static final int DEPTH_MAX = 4;

    public int CURRENT_STATE;


    private List<Map<Long,BDrawable>> bDrawables;
    private List<Map<Integer,Viewport>> viewPorts;
    private List<Map<Integer,List<Long>>> viewMapping; //MAP OF CAMERAS FOR EACH DEPTH AND REFERENCE IDS FOR OBJECTS NEED TO BE DRAWN USING THOSE CAMERAS
/*
    private List<Addition> additions;
    private List<Removal> removals;
    private List<Update> updates;
*/
    public RenderState(){

        bDrawables = new ArrayList<Map<Long, BDrawable>>();
        viewPorts = new ArrayList<Map<Integer, Viewport>>();
        viewMapping = new ArrayList<Map<Integer, List<Long>>>();
/*
        additions = new ArrayList<Addition>();
        removals = new ArrayList<Removal>();
        updates = new ArrayList<Update>();
*/
        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            bDrawables.add(depth,new HashMap<Long, BDrawable>());
            viewPorts.add(depth,new HashMap<Integer, Viewport>());
            viewMapping.add(depth,new HashMap<Integer, List<Long>>());
        }


    }

    public void addView(int _viewType, Viewport _viewPort){
        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            viewPorts.get(depth).put(_viewType, _viewPort);
        }
    }

    public void addElement(int _viewType, long _referenceId,int _depth,BDrawable _bDrawable){
       //additions.add(new Addition(_viewType,_referenceId,_depth,_bDrawable));
       bDrawables.get(_depth).put(_referenceId,_bDrawable.copy());
        if(!viewMapping.get(_depth).containsKey(_viewType)){
            List<Long> list = new ArrayList<Long>();
            list.add(_referenceId);
            viewMapping.get(_depth).put(_viewType,list);

        }else{
            viewMapping.get(_depth).get(_viewType).add(_referenceId);
        }
    }

    public void removeElement(long _referenceId,int _depth){
      // removals.add(new Removal(_referenceId,_depth));
        if(bDrawables.get(_depth).containsKey(_referenceId)){
            bDrawables.get(_depth).remove(_referenceId);
        }

        for(int map: viewMapping.get(_depth).keySet()){
            if(viewMapping.get(_depth).get(map).contains(_referenceId)){
                viewMapping.get(_depth).get(map).remove(_referenceId);
            }
        }
    }
    public void updateElement(long _referenceId,int _depth,BDrawable bDrawable){
           // updates.add(new Update(_referenceId, _depth, bDrawable));
            bDrawables.get(_depth).get(_referenceId).set(bDrawable);
    }


    //DEPTH BASE IS DRAWN FIRST
    //GAME SCREEN IS DRAWN LAST
    public void  render(SpriteBatch _spriteBatch) {
        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            for (int key : viewPorts.get(depth).keySet()) {
                viewPorts.get(depth).get(key).apply();
                _spriteBatch.setProjectionMatrix(viewPorts.get(depth).get(key).getCamera().combined);
                _spriteBatch.begin();
                if (viewMapping.get(depth).get(key) == null) {
                    _spriteBatch.end();
                    continue;
                }
                for (long MAPPER : viewMapping.get(depth).get(key)) {
                    bDrawables.get(depth).get(MAPPER).draw(_spriteBatch);
                }
                _spriteBatch.end();
            }
        }
    }

     public void getUpdates(RenderState _renderState) {
         for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            for(long key : _renderState.getDrawables().get(depth).keySet()){
                 if(!bDrawables.get(depth).containsKey(key)){
                     bDrawables.get(depth).put(key,_renderState.getDrawables().get(depth).get(key).copy());
                     //System.out.println("new");
                 }else{
                     bDrawables.get(depth).get(key).set(_renderState.getDrawables().get(depth).get(key));
                     //System.out.println("update");
                 }
             }
             for(long key : bDrawables.get(depth).keySet()){
                 if(!_renderState.getDrawables().get(depth).containsKey(key)){
                     bDrawables.get(depth).remove(key);
                 }
             }

             for(int viewKey : _renderState.getViewMapping().get(depth).keySet()){
                for(Long l : _renderState.getViewMapping().get(depth).get(viewKey)){
                    if(viewMapping.get(depth).get(viewKey)==null){
                        viewMapping.get(depth).put(viewKey,new ArrayList<Long>());
                    }
                    if (!viewMapping.get(depth).get(viewKey).contains(l)){
                        viewMapping.get(depth).get(viewKey).add(l);

                    }

                }
             }
             for(int viewKey : viewMapping.get(depth).keySet()){
                 for(Long l : viewMapping.get(depth).get(viewKey)){
                     if (!_renderState.getViewMapping().get(depth).get(viewKey).contains(l)){
                         viewMapping.get(depth).get(viewKey).remove(l);

                     }

                 }
             }
         }

/*
         for(Addition incomingAddition:_renderState.getAdditions()){
             bDrawables.get(incomingAddition.depth).put(incomingAddition.referenceId,incomingAddition.bDrawable.copy());
             if(!viewMapping.get(incomingAddition.depth).containsKey(incomingAddition.viewType)){
                 List<Long> list = new ArrayList<Long>();
                 list.add(incomingAddition.referenceId);
                 viewMapping.get(incomingAddition.depth).put(incomingAddition.viewType,list);

             }else{
                 viewMapping.get(incomingAddition.depth).get(incomingAddition.viewType).add(incomingAddition.referenceId);
             }
             if(incomingAddition.tracker>0){
                 Addition passingAddition = new Addition(incomingAddition.viewType,incomingAddition.referenceId,incomingAddition.depth,incomingAddition.bDrawable);
                 passingAddition.tracker = incomingAddition.tracker-1;
                 additions.add(passingAddition);

             }
         }

         for(Removal incomingRemoval:_renderState.getRemovals()){
             bDrawables.get(incomingRemoval.depth).remove(incomingRemoval.referenceId);
             for(int map: viewMapping.get(incomingRemoval.depth).keySet()){
                 if(viewMapping.get(incomingRemoval.depth).get(map).contains(incomingRemoval.referenceId)){
                     viewMapping.get(incomingRemoval.depth).get(map).remove(incomingRemoval.referenceId);
                 }
             }
             if(incomingRemoval.tracker>0){
                 Removal passingRemoval = new Removal(incomingRemoval.referenceId,incomingRemoval.depth);
                 passingRemoval.tracker = passingRemoval.tracker-1;
                 removals.add(passingRemoval);

             }

         }

         for(Update incomingUpdate:_renderState.getUpdates()){
             bDrawables.get(incomingUpdate.depth).get(incomingUpdate.referenceId).set(incomingUpdate.bDrawable);
             if(incomingUpdate.tracker>0){
                 Update passingUpdate = new Update(incomingUpdate.referenceId,incomingUpdate.depth,incomingUpdate.bDrawable);
                 passingUpdate.tracker = passingUpdate.tracker-1;
                 updates.add(passingUpdate);

             }

         }
*/
     }

    public void set(RenderState _renderState){
        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            bDrawables.get(depth).clear();
            viewPorts.get(depth).clear();
            viewMapping.get(depth).clear();
            for (Long key : _renderState.getDrawables().get(depth).keySet()) {
              bDrawables.get(depth).put(key, _renderState.getDrawables().get(depth).get(key)).copy();
            }

            for (Integer map : _renderState.getViewMapping().get(depth).keySet()) {
                viewMapping.get(depth).put(map, new ArrayList<Long>(_renderState.getViewMapping().get(depth).get(map)));
            }
            viewPorts.get(depth).putAll(_renderState.getViewPorts().get(depth));

        }
        CURRENT_STATE = _renderState.getCurrentState();
    }



    private List<Map<Long,BDrawable>> getDrawables(){return bDrawables;}
    private List<Map<Integer,Viewport>> getViewPorts(){return viewPorts;}
    private List<Map<Integer,List<Long>>> getViewMapping(){return viewMapping;}
    public int getCurrentState(){return CURRENT_STATE;}
    public void setCurrentState(int _state){CURRENT_STATE  = _state;}
/*
    public List<Addition> getAdditions(){
        List<Addition> list = new ArrayList<Addition>();
        list.addAll(additions);
        additions.clear();
        return list;
    }
    public List<Removal> getRemovals(){
        List<Removal> list = new ArrayList<Removal>();
        list.addAll(removals);
       removals.clear();
        return list;
    }

    public List<Update> getUpdates(){
        List<Update> list = new ArrayList<Update>();
        list.addAll(updates);
        updates.clear();
        return list;
    }
*/

}
