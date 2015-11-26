package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.handlers.GameStateManager;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class GameStateBase {
    protected GameStateManager GAME_STATE_MANAGER;
    protected AlphaAssault GAME;


    protected static int SCREEN_WIDTH = 800;
    protected static int SCREEN_HEIGHT = 400;

    public GameStateBase(GameStateManager _gameStateManager){
        GAME_STATE_MANAGER = _gameStateManager;
        GAME = GAME_STATE_MANAGER.game;


    }

    public abstract void handleInput();
    public abstract void update(float _deltaTime);
    public abstract void render();
    public abstract void dispose();
}
