package com.boomer.alphaassault;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.handlers.GameStateManager;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.settings.GameSettings;
import com.boomer.alphaassault.threads.RenderThread;
import com.boomer.alphaassault.threads.UpdateThread;

public class AlphaAssault extends RenderThread {
    UpdateThread updateThread = new UpdateThread();

    public AlphaAssault() {
        updateThread.start();
    }
}
