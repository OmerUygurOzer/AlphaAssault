package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.boomer.alphaassault.graphics.GUI.GamePad;
import com.boomer.alphaassault.graphics.GameGraphics;

/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager extends InputAdapter {


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
