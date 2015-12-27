package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.handlers.events.EventHandler;

/**
 * Created by Omer on 12/26/2015.
 */
public class NetworkThread extends BThread {

    private float timeAccumulated;
    private long time;
    private volatile boolean THREAD_RUNNING;


    private EventHandler eventHandler;

    public NetworkThread(EventHandler _eventHandler){

    }

    @Override
    public void run() {

    }
}
