package com.boomer.alphaassault.gamestates;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.boomer.alphaassault.GUI.Console;
import com.boomer.alphaassault.GUI.Hud;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.GUI.Analog;
import com.boomer.alphaassault.gameworld.gamelogic.Player;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.graphics.elements.BFont;
import com.boomer.alphaassault.graphics.views.GameViewport;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameStateBase {

    public static final int VIEW_TYPE_SCREEN = 0;
    public static final int VIEW_TYPE_GAME = 1;

    public static final long REFERENCE_POINT = 0;

    private Viewport screenView;
    private Viewport gameView;

    private OrthographicCamera screenCam;
    private SightCamera gameCam;

    //ADD CONTROLLERS
    private Analog analog;
    private Console console;

    //ADD HUD
    private Hud hud;

    //ADD IN-GAME FEATURES
    private GameWorld gameWorld;
    private Player player;


    public Play(GameStateManager _gameStateManager) {
        super(_gameStateManager);
        screenCam = new OrthographicCamera();
        screenView = new FitViewport(GameGraphics.VIRTUAL_WIDTH,GameGraphics.VIRTUAL_HEIGHT, screenCam);
        screenView.apply();
        screenCam.translate(GameGraphics.VIRTUAL_WIDTH/2,GameGraphics.VIRTUAL_HEIGHT/2);
        screenCam.update();

        gameCam = new SightCamera();
        gameView = new GameViewport(GameGraphics.VIRTUAL_HEIGHT,GameGraphics.VIRTUAL_HEIGHT, gameCam);
        gameView.apply();
        gameCam.update();

        RENDER_STATE.addView(VIEW_TYPE_SCREEN, screenView);
        RENDER_STATE.addView(VIEW_TYPE_GAME, gameView);
        RenderStateManager.setGameRenderState(RENDER_STATE);

        //ACTIVATE ANALOG CONTROLLER
        analog = new Analog(Analog.LEFT_ONLY);
        analog.setViewType(VIEW_TYPE_SCREEN);
        analog.setReferenceID(REFERENCE_POINT + 1000);
        analog.addToRenderState();

        //ACTIVATE CONSOLE CONTROLLER
        console = new Console();
        console.setReferenceID(REFERENCE_POINT + 2000);

        //ACTIVATE HUD
        hud = new Hud("Shakeah",null);
        hud.setReferenceID(REFERENCE_POINT + 3000);
        hud.setViewType(VIEW_TYPE_SCREEN);
        hud.addToRenderState();


        //ADD PLAYER
        player = new Player(gameCam);
        player.setAnalog(analog);
        player.setConsole(console);
        player.setHud(hud);

        //ACTIVATE GAMEWORLD
        gameWorld = new GameWorld(gameCam);
        gameWorld.setReferenceID(REFERENCE_POINT + 4000);
        gameWorld.addPlayer(player);
        gameWorld.setViewType(VIEW_TYPE_GAME);


        player.setRole(Player.ASSAULT_TROOPER);
        gameWorld.addToRenderState();

    }

    @Override
    public void render(SpriteBatch _spriteBatch) {
        RenderStateManager.renderingState.render(_spriteBatch);
    }

    @Override
    public void handleInput() {
        analog.receiveInput();
        console.receiveInput();
    }

    @Override
    public void update(float _deltaTime) {
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
