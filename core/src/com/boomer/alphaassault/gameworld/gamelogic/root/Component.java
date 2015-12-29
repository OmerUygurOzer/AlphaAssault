package com.boomer.alphaassault.gameworld.gamelogic.root;

/**
 * Created by Omer on 12/27/2015.
 */
public class Component {

    protected short id;
    protected Actor usingActor;


    protected Component(Actor _actor){
        usingActor = _actor;
    }

}
