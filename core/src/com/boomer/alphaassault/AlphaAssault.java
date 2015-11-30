package com.boomer.alphaassault;


import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.threads.InputThread;
import com.boomer.alphaassault.threads.RenderThread;
import com.boomer.alphaassault.threads.UpdateThread;
import com.boomer.alphaassault.utilities.Location;

import java.util.HashMap;

public class AlphaAssault extends RenderThread {
    private UpdateThread updateThread;
    private InputThread inputThread;

    private GameStateManager gameStateManager;
    private Resource gameResources;




    @Override
    public void create() {
        super.create();
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_ACTIVE;
        gameResources = new Resource();
        gameResources.initialize();
        gameStateManager = new GameStateManager();
        updateThread = new UpdateThread(gameStateManager);
        inputThread = new InputThread();
        setGameStateManager(gameStateManager);



    }

    @Override
    public void pause() {
        super.pause();
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_INACTIVE;

    }

    @Override
    public void resume() {
        super.resume();
        GameSettings.GAME_RUNNING_STATE = GameSettings.RUNNING_STATE_ACTIVE;

    }

    @Override
    public void dispose() {
        super.dispose();
        updateThread.stop();
        inputThread.stop();
        gameResources.disposeAll();
    }

    @Override
    public void resize(int _width, int _height) {
        gameStateManager.reSize(_width,_height);
    }


}
