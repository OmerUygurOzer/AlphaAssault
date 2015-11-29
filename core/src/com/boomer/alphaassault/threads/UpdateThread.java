package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class UpdateThread implements Runnable {

    private float TIME_ACCUMULATED;
    private long TIME;
    private boolean THREAD_RUNNING;
    private GameStateManager GAME_STATE_MANAGER;

    Thread UPDATE_THREAD;


    public UpdateThread(GameStateManager _gameStateManager) {
        UPDATE_THREAD = new Thread(this);
        TIME_ACCUMULATED = 0.0f;
        THREAD_RUNNING = true;
        GAME_STATE_MANAGER = _gameStateManager;
        UPDATE_THREAD.start();

    }

    @Override
    public void run() {
        while(THREAD_RUNNING) {
            while (GameSettings.GAME_RUNNING_STATE) {
                TIME_ACCUMULATED += getDeltaTime();

                while (TIME_ACCUMULATED >= GameSettings.UPS) {
                    TIME_ACCUMULATED -= GameSettings.UPS;


                   // System.out.println("UPDATE THREAD");
                    GAME_STATE_MANAGER.getGameState().handleInput();
                    GAME_STATE_MANAGER.getGameState().update(getDeltaTime());
                    RenderStateManager.update();
                }
                wait(1);

            }
        }

    }

    public void start(){
        UPDATE_THREAD.start();
    }

    private float getDeltaTime(){
        if(TIME_ACCUMULATED == 0){
            TIME = System.currentTimeMillis();
            return GameSettings.UPS+0.01f;
        }

        long deltaLong = System.currentTimeMillis() - TIME;
        TIME = System.currentTimeMillis();
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

    public  void stop(){
    THREAD_RUNNING = false;
    }


}
