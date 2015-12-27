package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;

/**
 * Created by Omer on 11/27/2015.
 */
public class UpdateThread extends BThread {


    private GameStateManager gameStateManager;
    private Thread updateThread;


    public UpdateThread(GameStateManager _gameStateManager) {
        updateThread = new Thread(this);
        timeAccumulated = 0.0f;
        THREAD_RUNNING = true;
        gameStateManager = _gameStateManager;
        System.out.println("UPDATE THREAD STARTED.");
        updateThread.start();

    }

    @Override
    public void run() {
        float deltaTime;
        while(THREAD_RUNNING) {
            while (GameSystem.GAME_RUNNING_STATE) {
                deltaTime = getDeltaTime();
                timeAccumulated += deltaTime;
                while (timeAccumulated >= GameSystem.UPS) {
                    //RenderStateManager.switchRenderState();
                    RenderStateManager.beginUpdating();
                    RenderStateManager.swapUpdates();

                    gameStateManager.getGameState().handleInput();
                    gameStateManager.getGameState().update(deltaTime);
                    timeAccumulated -= GameSystem.UPS;


                    RenderStateManager.releaseUpdatingState();
                }
                wait(1);

            }
            wait(1);
        }
        System.out.println("UPDATE THREAD FINISHED.");
    }

}
