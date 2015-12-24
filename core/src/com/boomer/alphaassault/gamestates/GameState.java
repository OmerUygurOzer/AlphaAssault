package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.handlers.GameStateManager;

import java.util.HashMap;

/**
 * Created by Omer on 11/24/2015.
 */
public abstract class GameState {

    protected GameStateManager GAME_STATE_MANAGER;
    protected RenderState RENDER_STATE;




    public GameState(GameStateManager _gameStateManager){
        GAME_STATE_MANAGER = _gameStateManager;
        RENDER_STATE = new RenderState(0);

    }

    public abstract void render(SpriteBatch _spriteBatch);
    public abstract void handleInput();
    public abstract void update(float _deltaTime);
    public abstract void dispose();
    public abstract void reSize(int _width,int _height);
}
