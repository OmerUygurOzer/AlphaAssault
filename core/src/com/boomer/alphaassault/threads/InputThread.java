package com.boomer.alphaassault.threads;

import com.boomer.alphaassault.GameSystem;
import com.boomer.alphaassault.handlers.controls.InputManager;

/**
 * Created by Omer on 11/27/2015.
 */
public class InputThread extends BThread {



    private Thread inputThread;
    private InputManager inputManager;



    public InputThread(InputManager _inputManager) {
        inputThread = new Thread(this);
        inputManager = _inputManager;
        inputManager.setLimit(GameSystem.INPUT_MAX);
        inputManager.setScreenBounds();

        timeAccumulated = 0.0f;
        THREAD_RUNNING = true;

        System.out.println("INPUT THREAD STARTED.");
        inputThread.start();

    }


    @Override
    public void run() {
        float deltaTime;
        while(THREAD_RUNNING) {
            while (GameSystem.GAME_RUNNING_STATE) {
                deltaTime = getDeltaTime();
                timeAccumulated += deltaTime;
                while (timeAccumulated >= GameSystem.IPS) {
                    timeAccumulated -= GameSystem.IPS;
                    inputManager.poll();

                }
                wait(1);

            }
            wait(1);
        }
        System.out.println("INPUT THREAD FINISHED.");
    }









}
