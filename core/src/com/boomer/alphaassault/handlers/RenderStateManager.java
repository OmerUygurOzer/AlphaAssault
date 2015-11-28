package com.boomer.alphaassault.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderStateManager {

    public static RenderState RENDER_STATE_ONE;
    public static RenderState RENDER_STATE_TWO;
    public static RenderState RENDER_STATE_THREE;

    public static RenderState RENDERING_STATE;
    public static RenderState UPDATING_STATE;
    public static RenderState UPDATED_STATE;

    public static RenderState DEFAULT;

    static{
        DEFAULT = new RenderState();
        RENDERING_STATE = DEFAULT;
        RENDER_STATE_ONE = new RenderState();
        RENDER_STATE_TWO = new RenderState();
        RENDER_STATE_THREE = new RenderState();
    }


    public static void add(int _type,Long _referenceID,Sprite _sprite, Location _location){
        if(RENDER_STATE_ONE.isEmpty() && RENDER_STATE_TWO.isEmpty() && RENDER_STATE_THREE.isEmpty()){
            DEFAULT =null;
            RENDER_STATE_ONE.add(_type,_referenceID,_sprite,_location);
            RENDER_STATE_TWO.add(_type,_referenceID,_sprite,_location);
            RENDER_STATE_THREE.add(_type,_referenceID,_sprite,_location);

            RENDER_STATE_ONE.CURRENT_STATE = RenderState.STATE_RECENTLY_UPDATED;
            RENDER_STATE_TWO.CURRENT_STATE = RenderState.STATE_BEING_RENDERED;
            RENDER_STATE_THREE.CURRENT_STATE = RenderState.STATE_BEING_UPDATED;

            UPDATED_STATE = RENDER_STATE_ONE;
            RENDERING_STATE = RENDER_STATE_TWO;
            UPDATING_STATE = RENDER_STATE_THREE;


           return;

        }

        UPDATING_STATE.add(_type,_referenceID,_sprite,_location);




    }

    public static void remove(Long _referenceID){

    }

    public static void update(){
            RenderState switcher;
            if(UPDATING_STATE == null && UPDATED_STATE ==null){return;}
            UPDATING_STATE.CURRENT_STATE = RenderState.STATE_RECENTLY_UPDATED;
            RENDERING_STATE.copy(UPDATING_STATE);
            RENDERING_STATE.CURRENT_STATE = RenderState.STATE_BEING_UPDATED;
            UPDATED_STATE.CURRENT_STATE = RenderState.STATE_BEING_RENDERED;
            switcher = UPDATING_STATE;
            UPDATING_STATE = RENDERING_STATE;
            RENDERING_STATE = UPDATED_STATE;
            UPDATED_STATE = switcher;



    }
}
