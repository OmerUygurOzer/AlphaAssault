package com.boomer.alphaassault.handlers;

import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.settings.GameSettings;


/**
 * Created by Omer on 11/27/2015.
 */
public class RenderStateManager {

    public static RenderState renderStateOne;
    public static RenderState renderStateTwo;
    public static RenderState renderStateThree;

    public static RenderState renderingState;
    public static RenderState updatingState;
    public static RenderState updatedState;

    public static RenderState DEFAULT;

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

    }


    public static void addElement(int _viewType, long _referenceID,int _depth ,BDrawable _bDrawable){
        updatingState.addElement(_viewType,_referenceID,_depth,_bDrawable);

    }

    public static void remove(long _referenceID, int _depth){
        updatingState.removeElement(_referenceID,_depth);

    }

    public static void updateElement(long _referenceId,int _depth,BDrawable _bDrawable){
        updatingState.updateElement(_referenceId,_depth, _bDrawable);
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
