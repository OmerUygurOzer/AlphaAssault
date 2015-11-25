package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.handlers.GameStateManager;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class GameStateBase {
    protected GameStateManager gameStateManager;
    protected AlphaAssault game;
    protected SpriteBatch spriteBatch;
    protected OrthographicCamera orthographicCamera;

    public GameStateBase(GameStateManager _gameStateManager){
        gameStateManager = _gameStateManager;
        game = gameStateManager.game;
        spriteBatch = game.spriteBatch;
        orthographicCamera = game.orthographicCamera;


    }

    public abstract void handleInput();
    public abstract void update(float deltaTime);
    public abstract void render();
    public abstract void dispose();
}
