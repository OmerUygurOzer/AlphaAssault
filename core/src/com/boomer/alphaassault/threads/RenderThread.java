package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderThread extends Game {

    private GameStateManager gameStateManager;
    private Resource gameResources;

    private float timePassed;
    public static SpriteBatch mainSpriteBatch;

    @Override
    public void create () {

        mainSpriteBatch = new SpriteBatch();

        gameResources = new Resource();
        gameResources.initialize();
        gameStateManager = new GameStateManager(this);
    }

    @Override
    public void resize(int width, int height) {
        //super.resize(width, height);
        gameStateManager.reSize(width,height);
    }

    @Override
    public void render () {
        if (GameSettings.GAME_RUNNING_STATE_ACTIVE) {
            super.render();
            timePassed += Gdx.graphics.getDeltaTime();
            while (timePassed >= GameSettings.RPS) {
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                timePassed -= GameSettings.RPS;
                System.out.println("RENDER");
                gameStateManager.update(GameSettings.RPS);
                gameStateManager.render();

            }
        }
    }
}

