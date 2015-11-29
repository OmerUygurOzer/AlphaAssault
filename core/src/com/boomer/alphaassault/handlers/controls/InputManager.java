package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.InputAdapter;
import com.boomer.alphaassault.graphics.GameGraphics;


/**
 * Created by Omer on 11/28/2015.
 */
public class InputManager extends InputAdapter {


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Inputs.beingDragged(screenX,GameGraphics.VIRTUAL_HEIGHT - screenY);
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Inputs.updateHover( screenX, GameGraphics.VIRTUAL_HEIGHT - screenY);
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Inputs.touchAcquire(screenX,GameGraphics.VIRTUAL_HEIGHT-screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Inputs.touchRelease(screenX,GameGraphics.VIRTUAL_HEIGHT-screenY);
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
