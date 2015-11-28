package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class RenderThread extends Game {


    private float timePassed;

    @Override
    public void create() {

    }

    @Override
    public void render () {
        if (GameSettings.GAME_RUNNING_STATE) {
            super.render();
            timePassed += Gdx.graphics.getDeltaTime();
            while (timePassed >= GameSettings.RPS) {
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                timePassed -= GameSettings.RPS;

                RenderStateManager.RENDERING_STATE.render();



            }
        }
    }
}

