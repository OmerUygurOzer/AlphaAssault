package com.boomer.alphaassault.gamestates;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.GUI.GamePad;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.views.GameViewport;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {

    public static final int VIEW_TYPE_SCREEN = 0;
    public static final int VIEW_TYPE_GAME = 1;

    private Viewport screenView;
    private Viewport gameView;

    private OrthographicCamera screenCam;
    private OrthographicCamera gameCam;

    //ADD CONTROLLERS
    private GamePad gamePad;

    //ADD IN-GAME FEATURES
    private GameWorld gameWorld;


    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        screenCam = new OrthographicCamera();
        screenView = new FitViewport(GameGraphics.VIRTUAL_WIDTH,GameGraphics.VIRTUAL_HEIGHT, screenCam);
        screenView.apply();
        screenCam.translate(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);
        screenCam.update();

        gameCam = new OrthographicCamera();
        gameView = new GameViewport(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT, gameCam);
        gameView.apply();
        gameCam.update();

        RENDER_STATE.addView(VIEW_TYPE_SCREEN, screenView);
        RENDER_STATE.addView(VIEW_TYPE_GAME, gameView);
        RenderStateManager.setGameRenderState(RENDER_STATE);

        //ACTIVATE GAME PAD
        gamePad = new GamePad(GamePad.BOTH);
        gamePad.setViewType(VIEW_TYPE_SCREEN);
        gamePad.addToRenderState();


        gameWorld = new GameWorld(gameCam);
        gameWorld.setViewType(VIEW_TYPE_GAME);
        gameWorld.setController(gamePad);
        gameWorld.addToRenderState();


    }

    @Override
    public void render(SpriteBatch _spriteBatch) {
        RenderStateManager.renderingState.render(_spriteBatch);
    }

    @Override
    public void handleInput() {
        gamePad.receiveInput();


    }

    @Override
    public void update(float _deltaTime) {
        //System.out.println(_deltaTime);
       gameWorld.update(_deltaTime);
    }


    @Override
    public void dispose() {

    }

    @Override
    public void reSize(int _width, int _height) {
      screenView.update(_width,_height);
      gameView.update(_width,_height);

    }
}
