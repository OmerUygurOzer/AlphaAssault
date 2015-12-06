package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.handlers.controls.InputManager;
import com.boomer.alphaassault.handlers.controls.Inputs;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class InputThread implements Runnable {

    private float timeAccumulated;
    private long time;
    private volatile boolean THREAD_RUNNING;

    private Thread inputThread;
    private InputManager inputManager;



    public InputThread(InputManager _inputManager) {
        inputThread = new Thread(this);
        inputManager = _inputManager;
        inputManager.setLimit(GameSettings.INPUT_MAX);
        inputManager.setScreenBounds();

        timeAccumulated = 0.0f;
        THREAD_RUNNING = true;

        System.out.println("INPUT THREAD STARTED.");
        inputThread.start();

    }


    @Override
    public void run() {

        while(THREAD_RUNNING) {
            while (GameSettings.GAME_RUNNING_STATE) {
                timeAccumulated += getDeltaTime();

                while (timeAccumulated >= GameSettings.IPS) {
                    timeAccumulated -= GameSettings.IPS;

                    inputManager.poll();

                    //System.out.println("INPUT");
                }
                wait(1);

            }
            wait(1);
        }
        System.out.println("INPUT THREAD FINISHED.");
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
