package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.GUI.Controller;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.units.AssaultTrooper;
import com.boomer.alphaassault.units.UnitBase;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {

    AssaultTrooper assaultTrooper;
    Controller controller;


    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        //assaultTrooper = new AssaultTrooper(UnitBase.TEAM_RED,new Location(0,0));
        controller = new Controller();


    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        AlphaAssault.mainSpriteBatch.begin();
        controller.render(AlphaAssault.mainSpriteBatch);
        AlphaAssault.mainSpriteBatch.end();



    }

    @Override
    public void dispose() {

    }
}
