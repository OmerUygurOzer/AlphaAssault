package com.boomer.alphaassault.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.settings.GameSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderStateManager {

    private static final int CYCLE_COMPLETE = 0;
    private static final int CYCLE_LAST = 1;
    private static final int CYCLE_BEGINNING = 2;
/*
    private static class Addition{
        public int tracker;
        public int viewType;
        public long referenceId;
        public int depth;
        public Sprite sprite;

        public Addition(int _viewType,long _referenceId,int _depth,Sprite _sprite){
            viewType = _viewType;
            referenceId = _referenceId;
            depth = _depth;
            sprite = _sprite;
            tracker = 2;
        }
    }

    private static class Removal{
        public int tracker;
        public long referenceId;
        public int depth;

        public Removal(long _referenceId,int _depth){
            referenceId = _referenceId;
            depth = _depth;
            tracker = 2;
        }
    }

*/



    public static RenderState renderStateOne;
    public static RenderState renderStateTwo;
    public static RenderState renderStateThree;

    public static RenderState renderingState;
    public static RenderState updatingState;
    public static RenderState updatedState;

    public static RenderState DEFAULT;
/*
    private static List<Addition> additionsOne;
    private static List<Addition> additionsTwo;

    private static List<Removal> removalsOne;
    private static List<Removal> removalsTwo;

    private static int cycle;
*/
    static{
        DEFAULT = new RenderState();
        renderingState = DEFAULT;
        renderStateOne = new RenderState();
        renderStateTwo = new RenderState();
        renderStateThree = new RenderState();


        renderStateOne.CURRENT_STATE = RenderState.STATE_RECENTLY_UPDATED;
        renderStateTwo.CURRENT_STATE = RenderState.STATE_BEING_RENDERED;
        renderStateThree.CURRENT_STATE = RenderState.STATE_BEING_UPDATED;

        updatedState = renderStateOne;
        renderingState = renderStateTwo;
        updatingState = renderStateThree;
/*
        additionsOne = new ArrayList<Addition>();
        additionsTwo = new ArrayList<Addition>();

        removalsOne = new ArrayList<Removal>();
        removalsTwo = new ArrayList<Removal>();

        cycle = CYCLE_BEGINNING;
*/

    }


    public static void addElement(int _viewType, long _referenceID,int _depth ,Sprite _sprite){
        updatingState.addElement(_viewType,_referenceID,_depth,new Sprite(_sprite));
       // additionsOne.add(new Addition(_viewType, _referenceID,_depth,new Sprite(_sprite)));
        //additionsTwo.add(new Addition(_viewType, _referenceID,_depth,new Sprite(_sprite)));
    }

    public static void remove(long _referenceID, int _depth){
        updatingState.removeElement(_referenceID,_depth);
        //removalsOne.add(new Removal(_referenceID,_depth));
        //removalsTwo.add(new Removal(_referenceID,_depth));
    }

    public static void switchRenderState(){
            RenderState switcher;
            if(updatingState == null && updatedState ==null){return;}

            //RE-ASSIGN STATES ACCORDINGLY

            updatingState.setCurrentState(RenderState.STATE_RECENTLY_UPDATED);
            renderingState.setCurrentState(RenderState.STATE_BEING_UPDATED);
            updatedState.setCurrentState(RenderState.STATE_BEING_RENDERED);

            switcher = updatingState;
            updatingState = renderingState;
            renderingState = updatedState;
            updatedState = switcher;




            updatingState.getUpdates(updatedState);


    }

    public static void setGameRenderState(RenderState _renderState){
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_INACTIVE;
        renderStateOne.set(_renderState);
        renderStateTwo.set(_renderState);
        renderStateThree.set(_renderState);
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_ACTIVE;

    }




}
