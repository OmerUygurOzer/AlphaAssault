package com.boomer.alphaassault.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.boomer.alphaassault.AlphaAssault;
import com.boomer.alphaassault.graphics.Controller;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.GameStateManager;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {


    Controller controller;
    OrthographicCamera playCamera;

    Viewport playViewPort;


    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        playCamera = new OrthographicCamera();

        playViewPort = new FitViewport(GameGraphics.VIRTUAL_WIDTH,GameGraphics.VIRTUAL_HEIGHT,playCamera);

        controller = new Controller();
        playCamera.translate(400,200);
        playCamera.update();



    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {


        AlphaAssault.mainSpriteBatch.setProjectionMatrix(playCamera.combined);
        AlphaAssault.mainSpriteBatch.begin();
        controller.render(AlphaAssault.mainSpriteBatch);
        AlphaAssault.mainSpriteBatch.end();





    }

    @Override
    public void dispose() {

    }

    @Override
    public void reSize(int _width, int _height) {
        playViewPort.update(_width,_height);

    }
}
