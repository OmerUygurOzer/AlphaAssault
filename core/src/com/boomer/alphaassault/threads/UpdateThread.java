package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class UpdateThread implements Runnable {

    private float timeAccumulated;
    private long time;
    private volatile boolean THREAD_RUNNING;

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
            while (GameSettings.GAME_RUNNING_STATE) {
                deltaTime = getDeltaTime();
                timeAccumulated += deltaTime;
                while (timeAccumulated >= GameSettings.UPS) {
                    gameStateManager.getGameState().handleInput();
                    gameStateManager.getGameState().update(timeAccumulated);
                    RenderStateManager.switchRenderState();
                    timeAccumulated -= GameSettings.UPS;
                }
                wait(1);

            }
            wait(1);
        }
        System.out.println("UPDATE THREAD FINISHED.");
    }


    private float getDeltaTime(){
        if(timeAccumulated == 0){
            time = System.currentTimeMillis();
            return GameSettings.UPS+0.01f;
        }

        long deltaLong = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        float deltaFloat=  (float)deltaLong/1000f;
        return deltaFloat;

    }

    private void wait(int _time){
        try {
            Thread.sleep(_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
    THREAD_RUNNING = false;
    }
    public void resume() {THREAD_RUNNING = true;}

}
