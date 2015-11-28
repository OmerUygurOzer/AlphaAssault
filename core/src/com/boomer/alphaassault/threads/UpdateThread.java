package com.boomer.alphaassault.threads;

import com.badlogic.gdx.Gdx;
import com.boomer.alphaassault.graphics.GameGraphics;
import com.boomer.alphaassault.settings.GameSettings;

/**
 * Created by Omer on 11/27/2015.
 */
public class UpdateThread implements Runnable {

    private float timeAccumulated;
    private long time;
    Thread updateThread;

    public UpdateThread() {
        updateThread = new Thread(this);
        timeAccumulated = 0.0f;

    }

    @Override
    public void run() {
        while(GameSettings.GAME_RUNNING_STATE_ACTIVE){
            timeAccumulated += getDeltaTime();

            while (timeAccumulated >= GameSettings.UPS) {
                timeAccumulated -= GameSettings.UPS;
                System.out.println("UPDATE");


            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }

    public void start(){
        updateThread.start();
    }

    private float getDeltaTime(){
        if(timeAccumulated == 0){
            time = System.currentTimeMillis();
            //System.out.println("ZERO");
            return GameSettings.UPS+0.1f;
        }

        long deltaLong = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        float deltaFloat=  (float)deltaLong/1000;
        //System.out.println(deltaFloat);
        return deltaFloat;

    }


}
