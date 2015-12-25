package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.Button;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/11/2015.
 */
public class ButtonWithGUI extends Button implements Renderable {
    private BSprite icon;
    private BSprite base;

    private short baseImageId;
    private short iconImageId;
    private int viewType;

    private Map<Integer,BSprite> states;

    private int numberOfStates;

    public ButtonWithGUI(float _x, float _y, int _width, int _height){
        super(_x,_y, _width,_height);
        numberOfStates = 0;
        states = new HashMap<Integer, BSprite>();
        baseImageId = GameSystem.obtainReference();
        iconImageId = GameSystem.obtainReference();
    }



    public void addState(int _state,TextureRegion _textureRegion){
        BSprite Bsprite = new BSprite(_textureRegion);
        if(numberOfStates == 0){base = Bsprite;}
        Bsprite.setSize(width,height);
        Bsprite.setCenter(center.x ,center.y);
        states.put(_state,Bsprite);
        numberOfStates++;
    }

    public void setIcon(TextureRegion _icon,float _width,float _height){
        icon = new BSprite(_icon);
        icon.setCenter(center.x,center.y);
        icon.setSize(_width,_height);
    }

    public void setState(int _state){
        BSprite getState = states.get(_state);
        RenderStateManager.updatingStatePointer.updateElement(baseImageId,RenderState.DEPTH_GAME_SCREEN_BASE,getState);
    }

    public void resetState(){
        BSprite getState = states.get(STATE_INIT);
        RenderStateManager.updatingStatePointer.updateElement(baseImageId,RenderState.DEPTH_GAME_SCREEN_BASE,getState);
    }

    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType, baseImageId, RenderState.DEPTH_GAME_SCREEN_BASE,base);
        RenderStateManager.updatingStatePointer.addElement(viewType, iconImageId,RenderState.DEPTH_GAME_SCREEN_DYNAMIC,icon);
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public short getReferenceID() {
        return baseImageId;
    }



    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }
}
