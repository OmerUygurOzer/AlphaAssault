package com.boomer.alphaassault;


import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.handlers.controls.InputManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.threads.InputThread;
import com.boomer.alphaassault.threads.RenderThread;
import com.boomer.alphaassault.threads.UpdateThread;

public class AlphaAssault extends RenderThread {


    private UpdateThread updateThread;
    private InputThread inputThread;

    private GameStateManager gameStateManager;
    private Resource gameResources;

    private InputManager inputManager;



    @Override
    public void create() {
        super.create();


        //INITIALIZE GAME RESOURCES
        gameResources = new Resource();
        gameResources.initialize();

        RenderStateManager.beginUpdating();
        RenderStateManager.swapUpdates();
        gameStateManager = new GameStateManager();
        RenderStateManager.releaseUpdatingState();

        //DECLARE THREADS
        updateThread = new UpdateThread(gameStateManager);
        inputManager = new InputManager();
        inputThread = new InputThread(inputManager);

        setGameStateManager(gameStateManager);

        GameSystem.GAME_RUNNING_STATE = GameSystem.RUNNING_STATE_ACTIVE;
    }

    @Override
    public void pause() {
        super.pause();
        GameSystem.GAME_RUNNING_STATE = GameSystem.RUNNING_STATE_INACTIVE;

    }

    @Override
    public void resume() {
        super.resume();
        GameSystem.GAME_RUNNING_STATE = GameSystem.RUNNING_STATE_ACTIVE;

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
        inputManager.setScreenBounds();

    }



}
