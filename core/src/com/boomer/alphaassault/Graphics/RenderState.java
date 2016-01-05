package com.boomer.alphaassault.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.graphics.elements.BDrawable;


import java.util.*;



/**
 * Created by Omer on 11/27/2015.
 */
public class RenderState{

    private int ID;
    public int currentFrame;

    public static final int DEPTH_BASE = 0;
    public static final int DEPTH_SURFACE = 1;
    public static final int DEPTH_AIR = 2;
    public static final int DEPTH_GAME_SCREEN_BASE = 3;
    public static final int DEPTH_GAME_SCREEN_DYNAMIC = 4;
    public static final int DEPTH_MAX = 5;

    private List<Map<Short,BDrawable>> bDrawables;
    private Map<Integer,Viewport> viewPorts;
    private List<Map<Integer,List<Short>>> viewMapping; //MAP OF CAMERAS FOR EACH DEPTH AND REFERENCE IDS FOR OBJECTS NEED TO BE DRAWN USING THOSE CAMERAS
    private List<Short> removals;

    private SpriteBatch spriteBatch;

    public RenderState(int _id){
        ID = _id;

        spriteBatch = new SpriteBatch();

        bDrawables = new ArrayList<Map<Short, BDrawable>>();
        viewPorts = new HashMap<Integer, Viewport>();
        viewMapping = new ArrayList<Map<Integer, List<Short>>>();
        removals = new ArrayList<Short>();

        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            bDrawables.add(depth,new HashMap<Short, BDrawable>());
            viewMapping.add(depth,new HashMap<Integer, List<Short>>());
        }

    }

    public void addView(int _viewType, Viewport _viewPort){
        viewPorts.put(_viewType, _viewPort);
    }

    public void addElement(int _viewType, short _referenceId,int _depth,BDrawable _bDrawable){

        bDrawables.get(_depth).put(_referenceId, _bDrawable.copy());
        if (!viewMapping.get(_depth).containsKey(_viewType)) {
            List<Short> list = new ArrayList<Short>();
            list.add(_referenceId);
            viewMapping.get(_depth).put(_viewType, list);

        } else {
            viewMapping.get(_depth).get(_viewType).add(_referenceId);
        }

    }


    public void removeElement(Short _referenceId,int _depth){

        if (bDrawables.get(_depth).containsKey(_referenceId)) {
            bDrawables.get(_depth).remove(_referenceId);
        }

        for (int map : viewMapping.get(_depth).keySet()) {
            if (viewMapping.get(_depth).get(map).contains(_referenceId)) {
                viewMapping.get(_depth).get(map).remove(_referenceId);
            }
        }


    }


    public void updateElement(short _referenceId,int _depth,BDrawable bDrawable){
        bDrawables.get(_depth).get(_referenceId).set(bDrawable);
    }




    //DEPTH BASE IS DRAWN FIRST
    //GAME SCREEN IS DRAWN LAST
    public void  render() {

        for (int depth = DEPTH_BASE; depth < DEPTH_MAX; depth++) {
            for (int key : viewPorts.keySet()) {
                spriteBatch.setProjectionMatrix(viewPorts.get(key).getCamera().combined);
                viewPorts.get(key).apply();
                spriteBatch.begin();
                if (viewMapping.get(depth).get(key) != null) {
                    for (Short MAPPER : viewMapping.get(depth).get(key)) {
                        bDrawables.get(depth).get(MAPPER).draw(spriteBatch);
                    }
                }
                spriteBatch.end();
            }
        }


    }

    public void getUpdates(RenderState _renderState) {

        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            for(Short key : _renderState.getDrawables().get(depth).keySet()){
                if(!bDrawables.get(depth).containsKey(key)){
                    bDrawables.get(depth).put(key,_renderState.getDrawables().get(depth).get(key).copy());

                }else{
                    bDrawables.get(depth).get(key).set(_renderState.getDrawables().get(depth).get(key));

                }
            }

            for(Short key : bDrawables.get(depth).keySet()){
                if(!_renderState.getDrawables().get(depth).containsKey(key)){
                    removals.add(key);
                }
            }
            for(short key : removals){
                bDrawables.get(depth).remove(key);
            }
            removals.clear();


            for(int viewKey : _renderState.getViewMapping().get(depth).keySet()){
                for(Short l : _renderState.getViewMapping().get(depth).get(viewKey)){
                    if(viewMapping.get(depth).get(viewKey)==null){
                        viewMapping.get(depth).put(viewKey,new ArrayList<Short>());
                    }
                    if (!viewMapping.get(depth).get(viewKey).contains(l)){
                        viewMapping.get(depth).get(viewKey).add(l);

                    }

                }
            }


            for(int viewKey : viewMapping.get(depth).keySet()){
                for(Short l : viewMapping.get(depth).get(viewKey)){
                    if (!_renderState.getViewMapping().get(depth).get(viewKey).contains(l)){
                        removals.add(l);
                    }

                }
            }

            for(int viewKey : viewMapping.get(depth).keySet()){
                for(Short l : removals){
                    if(viewMapping.get(depth).get(viewKey).contains(l))
                        viewMapping.get(depth).get(viewKey).remove(l);
                }
            }
            removals.clear();


        }


    }

    public void set(RenderState _renderState){
        viewPorts.clear();
        viewPorts.putAll(_renderState.getViewPorts());
        for(int depth = DEPTH_BASE;depth<DEPTH_MAX;depth++) {
            bDrawables.get(depth).clear();

            viewMapping.get(depth).clear();
            for (short key : _renderState.getDrawables().get(depth).keySet()) {
                bDrawables.get(depth).put(key, _renderState.getDrawables().get(depth).get(key)).copy();
            }

            for (Integer map : _renderState.getViewMapping().get(depth).keySet()) {
                viewMapping.get(depth).put(map, new ArrayList<Short>(_renderState.getViewMapping().get(depth).get(map)));
            }

        }

    }



    private List<Map<Short,BDrawable>> getDrawables(){return bDrawables;}
    private Map<Integer,Viewport> getViewPorts(){return viewPorts;}
    private List<Map<Integer,List<Short>>> getViewMapping(){return viewMapping;}
    public void dispose(){spriteBatch.dispose();}


}