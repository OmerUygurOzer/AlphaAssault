package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.handlers.controls.InputManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class InputThread implements Runnable {
    private float TIME_ACCUMULATED;
    private long TIME;
    private boolean THREAD_RUNNING;

    Thread INPUT_THREAD;
   // InputManager INPUT_MANAGER;


    public InputThread() {
        INPUT_THREAD = new Thread(this);
        TIME_ACCUMULATED = 0.0f;
        THREAD_RUNNING = true;


        INPUT_THREAD.start();

    }

    @Override
    public void run() {
        InputManager INPUT_MANAGER;
        INPUT_MANAGER = new InputManager();
        Gdx.input.setInputProcessor(INPUT_MANAGER);
        while(THREAD_RUNNING) {
            while (GameSettings.GAME_RUNNING_STATE) {
                TIME_ACCUMULATED += getDeltaTime();

                while (TIME_ACCUMULATED >= GameSettings.IPS) {
                    TIME_ACCUMULATED -= GameSettings.IPS;
                    //System.out.println("INPUT");



                }
                wait(1);

            }
        }

    }

    public void start(){
        INPUT_THREAD.start();
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

    public synchronized void stop(){
        THREAD_RUNNING = false;
    }




}
