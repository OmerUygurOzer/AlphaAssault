package com.boomer.alphaassault.gamestates;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.*;
import com.boomer.alphaassault.GUI.ConsoleWithGUI;
import com.boomer.alphaassault.GUI.Hud;
import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.GUI.AnalogWithGUI;
import com.boomer.alphaassault.gameworld.players.Human;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.graphics.cameras.SightCamera;
import com.boomer.alphaassault.graphics.views.GameViewport;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 11/24/2015.
 */
public class Play extends GameState {

    public static final int VIEW_TYPE_SCREEN = 0;
    public static final int VIEW_TYPE_GAME = 1;

    public static final long REFERENCE_POINT = 0;

    //VIEWPORTS
    private Viewport screenView;
    private Viewport gameView;

    //CAMERAS
    private OrthographicCamera screenCam;
    private SightCamera gameCam;

    //ADD CONTROLLERS
    private AnalogWithGUI analog;
    private ConsoleWithGUI console;

    //ADD HUD
    private Hud hud;

    //ADD IN-GAME FEATURES
    private GameWorld gameWorld;
    private Human player;


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
        analog = new AnalogWithGUI(AnalogWithGUI.LEFT_ONLY);
        analog.setViewType(VIEW_TYPE_SCREEN);
        analog.addToRenderState();

        //ACTIVATE CONSOLE CONTROLLER
        console = new ConsoleWithGUI();

        //ACTIVATE HUD
        hud = new Hud();
        hud.setViewType(VIEW_TYPE_SCREEN);

        //ACTIVATE GAMEWORLD
        gameWorld = new GameWorld(gameCam);
        gameWorld.setViewType(VIEW_TYPE_GAME);

        //ADD PLAYER
        Unit assaultTrooper = new Unit(GameSystem.TEAM_BLUE,new Vector2(0,0),gameWorld);
        player = new Human(gameCam,gameWorld,assaultTrooper);
        player.setViewType(VIEW_TYPE_GAME);
        player.setName("PC PRINCIPAL");
        player.setIcon(Resource.getTexture(Resource.TEXTURE_PLAYER));
        player.setAnalog(analog);
        player.setConsole(console);
        player.setHud(hud);

        gameWorld.addPlayer(player);
        gameWorld.addToRenderState();

    }

    @Override
    public void render(SpriteBatch _spriteBatch) {
        RenderStateManager.renderingStatePointer.render();
    }

    @Override
    public void handleInput() {
        analog.receiveInput();
        console.receiveInput();
    }

    @Override
    public void update(float _deltaTime) {
        gameWorld.update(_deltaTime);
        hud.update(_deltaTime);
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
