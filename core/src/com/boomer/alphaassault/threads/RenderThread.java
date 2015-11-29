package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderThread extends Game {


    private float TIME_ACCUMULATED;
    private SpriteBatch SPRITE_BATCH;
    private GameStateManager GAME_STATE_MANAGER;

    public void setGameStateManager(GameStateManager _gameStateManager){
        GAME_STATE_MANAGER = _gameStateManager;
    }

    @Override
    public void create() {
            SPRITE_BATCH = new SpriteBatch();
    }

    @Override
    public void render () {
        if (GameSettings.GAME_RUNNING_STATE) {
            super.render();
            TIME_ACCUMULATED += Gdx.graphics.getDeltaTime();
            Gdx.gl.glClearColor(0,0,0,1);
            while (TIME_ACCUMULATED >= GameSettings.RPS) {
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                TIME_ACCUMULATED -= GameSettings.RPS;

                //System.out.println("RENDER THREAD");
                GAME_STATE_MANAGER.render(SPRITE_BATCH);



            }
        }
    }
}

