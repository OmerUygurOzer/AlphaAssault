package com.boomer.alphaassault.gameworld;

import com.boomer.alphaassault.utilities.Renderable;
import com.boomer.alphaassault.utilities.Updateable;

/**
 * Created by Omer on 11/24/2015.
 */
public class GameWorld implements Updateable,Renderable{


    Map gameMap;

    public GameWorld(){
        gameMap = new Map(Map.SIZE_SMALL);
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
