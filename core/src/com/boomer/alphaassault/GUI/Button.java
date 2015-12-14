package com.boomer.alphaassault.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Omer on 12/11/2015.
 */
public class Button implements Renderable {
    private BSprite icon;

    private float centerX;
    private float centerY;

    private int width;
    private int height;

    private long referenceId;
    private int viewType;

    private Map<Integer,BSprite> states;

    private static final int STATE_INIT = 0;
    private int numberOfStates;

    public Button(float _x,float _y,int _width,int _height){
        numberOfStates = 0;
        states = new HashMap<Integer, BSprite>();
        centerX = _x;
        centerY = _y;
        width = _width;
        height = _height;
    }


    public float getCenterX(){return centerX;}
    public float getCenterY(){return centerY;}

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public void addState(int _state,TextureRegion _textureRegion){
        BSprite Bsprite = new BSprite(_textureRegion);
        if(numberOfStates == 0){icon = Bsprite;}
        Bsprite.setSize(width,height);
        Bsprite.setCenter(centerX,centerY);
        states.put(_state,Bsprite);
        numberOfStates++;
    }

    public void setState(int _state){
        BSprite getState = states.get(_state);
        RenderStateManager.updateElement(referenceId,RenderState.DEPTH_GAME_SCREEN,getState);
    }

    public void resetState(){
        BSprite getState = states.get(STATE_INIT);
        RenderStateManager.updateElement(referenceId,RenderState.DEPTH_GAME_SCREEN,getState);
    }

    @Override
    public void addToRenderState() {
        RenderStateManager.addElement(viewType,referenceId, RenderState.DEPTH_GAME_SCREEN,icon);
    }

    @Override
    public void removeFromRenderState() {

    }

    @Override
    public long getReferenceID() {
        return referenceId;
    }

    @Override
    public void setReferenceID(long _referenceId) {
        referenceId = _referenceId;
    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }
}
