package core.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import core.Resources;
import core.System;
import core.graphics.GUI.MapHolder;
import core.graphics.GUI.BScreen;
import core.graphics.GUI.TabSelector;
import core.inputs.InputManager;
import core.level.Level;
import core.level.LevelPacker;
import core.map.Map;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditor extends Game {

    private SpriteBatch spriteBatch;
    private InputManager inputManager;

    private float timeAccumulated;

    private MapHolder mapHolder;
    private Map map;

    private TabSelector entityPalette;

    private Viewport generalView;
    private OrthographicCamera generalCamera;

    private BScreen screen;

    private Level level;

    @Override
    public void create() {
        System.init();
        Resources.initialize();

        level = new Level();

        screen = new BScreen();

        spriteBatch = new SpriteBatch();

        generalCamera = new OrthographicCamera();
        generalView = new FitViewport(System.VIRTUAL_WIDTH,System.VIRTUAL_HEIGHT,generalCamera);
        generalView.apply();
        generalCamera.translate(System.VIRTUAL_WIDTH / 2, System.VIRTUAL_HEIGHT / 2);
        generalCamera.update();


        timeAccumulated = 0f;

        inputManager = new InputManager();
        inputManager.setLimit(4);

        map = new Map(Map.SMALL);
        mapHolder = new MapHolder(10,10,660,660,map);


        entityPalette = new TabSelector(810,728);
        entityPalette.setMapHolder(mapHolder);
        entityPalette.setCamera(generalCamera);
        entityPalette.setView(generalView);

        screen.addComponent(entityPalette);
        screen.addComponent(mapHolder);

        level.setMap(map);

        LevelPacker.pack("test",null);

    }



    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
        timeAccumulated+= Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        if(timeAccumulated >= System.FPS){

            timeAccumulated = 0f;
            handleInputs();



            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            mapHolder.receiveInput();
            entityPalette.receiveInput();

            screen.draw(spriteBatch);


        }

    }

    public void handleInputs(){
        inputManager.poll();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        generalView.update(width,height);
        inputManager.setScreenBounds();
        mapHolder.resize(generalView.getScreenWidth(),generalView.getScreenHeight());

    }

    @Override
    public void setScreen(Screen Screen) {
        super.setScreen(Screen);
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }




}
