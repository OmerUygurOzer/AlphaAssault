package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.GUI.Controller;
import com.boomer.alphaassault.handlers.GameStateManager;
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
        assaultTrooper = new AssaultTrooper(UnitBase.TEAM_RED,new Location(320,320));
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
        System.out.println("WIDTH"+Gdx.graphics.getWidth());
        System.out.println("HEIGHT"+Gdx.graphics.getHeight());
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        spriteBatch.begin();
        assaultTrooper.render(spriteBatch);
        controller.render(spriteBatch);
        spriteBatch.end();

    }

    @Override
    public void dispose() {

    }
}
