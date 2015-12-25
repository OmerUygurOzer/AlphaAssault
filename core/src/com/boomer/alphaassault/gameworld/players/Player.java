package com.boomer.alphaassault.gameworld.players;


import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Updateable;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.Renderable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Omer on 12/6/2015.
 */
public abstract class Player implements Updateable{
    private List<Unit> unitsControlled;

    protected GameWorld world;

    protected Player(GameWorld _world){
        unitsControlled = new ArrayList<Unit>();

        world = _world;
    }

    public final void addControlledUnit(Unit _unit){
        unitsControlled.add(_unit);
    }



}
