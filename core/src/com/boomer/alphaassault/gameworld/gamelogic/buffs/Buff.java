package com.boomer.alphaassault.gameworld.gamelogic.buffs;

import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 12/12/2015.
 */
public abstract class Buff implements Updateable,Renderable {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    private long referenceId;
    private int viewType;

    private long duration;
    private long startTime;

    private boolean isExpired;

    protected BSprite icon;

    public Buff(long _duration){
        duration = _duration;
        startTime = System.currentTimeMillis();
        isExpired = false;
    }



    @Override
    public void update(float _deltaTime) {
        isExpired = (System.currentTimeMillis()-startTime) > duration;
    }

    public boolean isExpired(){
        return isExpired;
    }

    public void inflict(Unit _unit){
        _unit.addBuff(this);
        _unit.getPlayer().getHud().showBuff(this);
    }

    public void deflict(Unit _unit){
        _unit.removeBuff(this);
        _unit.getPlayer().getHud().removeBuff(this);
    }

    public BSprite getIcon(){return icon;}

    @Override
    public void addToRenderState() {
        RenderStateManager.addElement(viewType,referenceId, RenderState.DEPTH_GAME_SCREEN,icon);
    }

    @Override
    public void removeFromRenderState() {
        RenderStateManager.removeElement(referenceId,RenderState.DEPTH_GAME_SCREEN);
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
