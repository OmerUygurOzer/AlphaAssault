package com.boomer.alphaassault.gameworld.visuals;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.graphics.elements.BDrawable;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 12/23/2015.
 */
public abstract class Visual extends Entity implements Renderable{

    protected BDrawable effect;
    protected long duration;
    protected long initTime;

    protected boolean cycleUponFinish;
    protected int cycleToFrame;
    protected int totalFrames;
    protected int currentFrame;
    protected int secondsPerFrame;

    protected int viewType;

    protected Visual(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
        isSolid = false;
        initTime = System.currentTimeMillis();
    }

    public final void setCycleUponFinish(boolean _cup){
        cycleUponFinish = _cup;
    }

    public final void setCycleToFrame(int _ctf){
        cycleToFrame = _ctf;
    }

    public final void setSecondsPerFrame(int _spf){
        secondsPerFrame = _spf;
    }

    public final void setDuration(float _duration){
        duration = (long)(_duration * 1000);
    }



    @Override
    public void removeFromRenderState() {
        RenderStateManager.updatingStatePointer.removeElement(referenceId,depth);
    }

    @Override
    public void setViewType(int _viewType) {
        viewType = _viewType;
    }

    @Override
    public short getReferenceID() {
        return referenceId;
    }

    @Override
    public void addToRenderState() {
        RenderStateManager.updatingStatePointer.addElement(viewType,referenceId,depth,effect);
    }
}
