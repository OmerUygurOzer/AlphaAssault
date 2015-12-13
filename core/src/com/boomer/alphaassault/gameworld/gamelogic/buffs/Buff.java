package com.boomer.alphaassault.gameworld.gamelogic.buffs;

import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 12/12/2015.
 */
public abstract class Buff implements Updateable {

    private long duration;
    private long startTime;

    private boolean isExpired;

    public Buff(float _duration){
        duration = Math.round(duration * 1000);
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
    }

    public void deflict(Unit _unit){
        _unit.removeBuff(this);
    }
}
