package com.boomer.alphaassault.gameworld.units.skills;

/**
 * Created by Omer on 12/1/2015.
 */
public abstract class Skill {
    //VARIABLES
    protected int cooldown;
    protected long timer;
    protected int supply;
    protected boolean ready;

    //METHODS
    public abstract void resupply();
    public abstract void use();
    public abstract void update();
    public boolean isReady(){return ready;}
}
