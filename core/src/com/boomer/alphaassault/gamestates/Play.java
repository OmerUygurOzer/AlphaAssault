package com.boomer.alphaassault.gamestates;


import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.gameworld.units.AssaultTrooper;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {



    Viewport playViewPort;
   AssaultTrooper assaultTrooper = new AssaultTrooper(GameSettings.TEAM_BLUE,new Location(200,200));


    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);




    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }


    @Override
    public void dispose() {

    }

    @Override
    public void reSize(int _width, int _height) {
        GameGraphics.SCREEN_VIEW.update(_width,_height);
        GameGraphics.MAP_VIEW.update(_width,_height);
    }
}
