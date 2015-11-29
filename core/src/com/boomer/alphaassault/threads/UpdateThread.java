package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class UpdateThread implements Runnable {

    private float TIME_ACCUMULATED;
    private long TIME;
    private boolean THREAD_RUNNING;

    Thread UPDATE_THREAD;


    public UpdateThread() {
        UPDATE_THREAD = new Thread(this);
        TIME_ACCUMULATED = 0.0f;
        THREAD_RUNNING = true;
        UPDATE_THREAD.start();

    }

    @Override
    public void run() {
        while(THREAD_RUNNING) {
            while (GameSettings.GAME_RUNNING_STATE) {
                TIME_ACCUMULATED += getDeltaTime();

                while (TIME_ACCUMULATED >= GameSettings.UPS) {
                    TIME_ACCUMULATED -= GameSettings.UPS;
                    //System.out.println("UPDATE THREAD");
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

    public synchronized void stop(){
    THREAD_RUNNING = false;
    }


}
