package com.boomer.alphaassault.gameworld;

import com.boomer.alphaassault.handlers.controls.Controller;
import com.boomer.alphaassault.utilities.Renderable;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 11/24/2015.
 */

    /*
    GAMEWORLD:  ALL IN-GAME OBJECTS ARE COLLECTED HERE
     */
public class GameWorld implements Updateable,Renderable{

    Controller controller;
    private Map gameMap;

    public GameWorld(){
        gameMap = new Map(Map.SIZE_SMALL);
    }

    public void setController(Controller _controller){
        controller = _controller;
    }

    @Override
    public void addToRenderState() {
        gameMap.addToRenderState();
    }

    @Override
    public long getReferenceID() {
        return 0;
    }

    @Override
    public void setCameraType(int _cameraType) {
        gameMap.setCameraType(_cameraType);
    }

    @Override
    public void update() {

    }
}
