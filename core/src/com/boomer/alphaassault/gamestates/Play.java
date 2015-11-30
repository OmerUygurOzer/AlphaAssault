package com.boomer.alphaassault.gamestates;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.boomer.alphaassault.gameworld.units.AssaultTrooper;
import com.boomer.alphaassault.graphics.GUI.GamePad;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {

    public static final int CAMERA_TYPE_SCREEN = 0;
    public static final int CAMERA_TYPE_MAP = 1;

    private Viewport SCREEN_VIEW;
    private Viewport MAP_VIEW;

    private OrthographicCamera SCREEN_CAM;
    private OrthographicCamera MAP_CAM;

    AssaultTrooper assaultTrooper;
    GamePad GAME_PAD;

    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);



        SCREEN_CAM = new OrthographicCamera();
        SCREEN_VIEW = new FitViewport(GameGraphics.VIRTUAL_WIDTH,GameGraphics.VIRTUAL_HEIGHT,SCREEN_CAM);
        SCREEN_VIEW.apply();
        SCREEN_CAM.translate(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);
        SCREEN_CAM.update();

        MAP_CAM = new OrthographicCamera();
        MAP_VIEW = new FitViewport(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT,MAP_CAM);
        MAP_VIEW.apply();
        MAP_CAM.translate(GameGraphics.VIRTUAL_HEIGHT/2,GameGraphics.VIRTUAL_HEIGHT/2);
        MAP_CAM.update();

        RENDER_STATE.addCamera(CAMERA_TYPE_SCREEN,SCREEN_CAM);
        RENDER_STATE.addCamera(CAMERA_TYPE_MAP,MAP_CAM);

        GAME_PAD = new GamePad(GamePad.LEFT);

        RenderStateManager.setGameRenderState(RENDER_STATE);
        GAME_PAD.setCameraType(CAMERA_TYPE_SCREEN);
        GAME_PAD.addToRenderState();

        assaultTrooper = new AssaultTrooper(GameSettings.TEAM_BLUE,new Location(200,200));
        assaultTrooper.setCameraType(CAMERA_TYPE_MAP);
        assaultTrooper.addToRenderState();
    }

    @Override
    public void render(SpriteBatch _spriteBatch) {
        try {
            RenderStateManager.renderingState.render(_spriteBatch);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleInput() {

        GAME_PAD.receiveInput();
    }

    @Override
    public void update(float deltaTime) {

    }


    @Override
    public void dispose() {

    }

    @Override
    public void reSize(int _width, int _height) {
      SCREEN_VIEW.update(_width,_height);


    }
}
