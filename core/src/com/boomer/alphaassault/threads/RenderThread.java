package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderThread extends Game {


    private float timeAccumulated;
    private SpriteBatch spriteBatch;
    private GameStateManager gameStateManager;

    public void setGameStateManager(GameStateManager _gameStateManager){
        gameStateManager = _gameStateManager;
    }

    @Override
    public void create() {
            spriteBatch = new SpriteBatch();
            System.out.println("RENDER THREAD STARTED.");
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        RenderStateManager.dispose();
        System.out.println("RENDER THREAD FINISHED.");
    }

    @Override
    public void render () {
        if (GameSystem.GAME_RUNNING_STATE) {
            super.render();
            timeAccumulated += Gdx.graphics.getDeltaTime();
            Gdx.gl.glClearColor(0,0,0,1);
            while (timeAccumulated >= GameSystem.RPS) {
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                timeAccumulated -= GameSystem.RPS;
                RenderStateManager.renderingStatePointer = RenderStateManager.getRenderingState();
                gameStateManager.getGameState().render(null);
                RenderStateManager.releaseRenderingState();
            }
            wait(1);
        }
    }

    private void wait(int _time){
        try {
            Thread.sleep(_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

