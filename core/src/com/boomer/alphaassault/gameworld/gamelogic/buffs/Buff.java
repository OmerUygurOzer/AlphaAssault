package com.boomer.alphaassault.gameworld.gamelogic.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.gameworld.units.UnitBase;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 12/12/2015.
 */
public abstract class Buff implements Updateable,Renderable {

    public static final int SIZE = 40;

    private short referenceId;
    private int viewType;

    private long duration;
    private long startTime;

    private boolean isExpired;

    protected BSprite icon;
    protected TextureRegion image;

    protected Vector2 center;

    public Buff(long _duration){
        duration = _duration;
        startTime = System.currentTimeMillis();
        isExpired = false;
        referenceId = GameSystem.obtainReference();
        center = new Vector2();
    }

    public void setIconPosition(float _x,float _y){
        center.x = _x;
        center.y = _y;
        icon.setCenter(_x,_y);
    }


    @Override
    public void update(float _deltaTime) {
        isExpired = (System.currentTimeMillis()-startTime) > duration;
    }

    public boolean isExpired(){
        return isExpired;
    }

    public void inflict(UnitBase _unit){
        _unit.addBuff(this);

    }

    public void deflict(UnitBase _unit){
        _unit.removeBuff(this);

    }

    public BSprite getIcon(){return icon;}

    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId, RenderState.DEPTH_GAME_SCREEN_BASE,icon);

    }

    @Override
    public void removeFromRenderState() {
        RenderStateManager.updatingStatePointer.removeElement(referenceId,RenderState.DEPTH_GAME_SCREEN_BASE);

    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }


    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }
}
