package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.Game;
import com.boomer.alphaassault.handlers.GameStateManager;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class GameStateBase {
    protected GameStateManager GAME_STATE_MANAGER;
    protected Game GAME;


    public GameStateBase(GameStateManager _gameStateManager){
        GAME_STATE_MANAGER = _gameStateManager;
        GAME = GAME_STATE_MANAGER.game;


    }

    public abstract void handleInput();
    public abstract void update(float _deltaTime);
    public abstract void render();
    public abstract void dispose();
    public abstract void reSize(int _width,int _height);
}
