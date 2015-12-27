package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.GameSystem;

/**
 * Created by Omer on 12/26/2015.
 */
public abstract class BThread implements Runnable {

    protected float timeAccumulated;
    protected long time;
    protected volatile boolean THREAD_RUNNING;

    protected final float getDeltaTime(){
        if(timeAccumulated == 0){
            time = System.currentTimeMillis();
            return GameSystem.UPS+0.01f;
        }

        long deltaLong = System.currentTimeMillis() - time;
        time = System.currentTimeMillis();
        float deltaFloat=  (float)deltaLong/1000f;
        return deltaFloat;

    }

    protected final void wait(int _time){
        try {
            Thread.sleep(_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void  stop(){
        THREAD_RUNNING = false;
    }
    public final void resume() {THREAD_RUNNING = true;}
}
