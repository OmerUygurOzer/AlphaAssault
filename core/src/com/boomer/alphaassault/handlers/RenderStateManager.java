package com.boomer.alphaassault.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;

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


    public static void add(int _type,Long _referenceID,Sprite _sprite){
        updatingState.add(_type,_referenceID,_sprite);

    }

    public static void remove(Long _referenceID){

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
            updatingState.copy(updatedState);


    }

    public static void setGameRenderState(RenderState _renderState){
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_INACTIVE;
        renderStateOne.copy(_renderState);
        renderStateTwo.copy(_renderState);
        renderStateThree.copy(_renderState);
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_ACTIVE;

    }


}
