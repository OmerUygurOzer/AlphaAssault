package core.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import core.Resources;
import core.System;
import core.graphics.GUI.MapHolder;
import core.inputs.InputManager;

/**
 * Created by Omer on 12/28/2015.
 */
public class MapEditor extends Game {

    private SpriteBatch spriteBatch;

    private InputManager inputManager;

    private float timeAccumulated;

    private MapHolder mapHolder;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        System.init();

        Resources.initialize();

        timeAccumulated = 0f;

        inputManager = new InputManager();
        inputManager.setLimit(4);

        mapHolder = new MapHolder(100,100,400,400);
    }



    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
        timeAccumulated+= Gdx.graphics.getDeltaTime();
        spriteBatch.setProjectionMatrix(mapHolder.getViewport().getCamera().combined);
        Gdx.gl.glClearColor(0,0,0,1);
        if(timeAccumulated >= System.FPS){
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            timeAccumulated = 0f;
            handleInputs();
            mapHolder.receiveInput();
            spriteBatch.begin();
            mapHolder.draw(spriteBatch);
            spriteBatch.end();

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
        inputManager.setScreenBounds();
        mapHolder.resize(width,height);
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

    @Override
    public Screen getScreen() {
        return super.getScreen();
    }




}
