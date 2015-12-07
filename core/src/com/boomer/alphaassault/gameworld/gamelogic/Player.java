package com.boomer.alphaassault.gameworld.gamelogic;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.boomer.alphaassault.GUI.GamePad;
import com.boomer.alphaassault.handlers.controls.Controller;


/**
 * Created by Omer on 12/6/2015.
 */
public class Player {
    Camera camera;
    Controller controller;

    public Player(Camera _camera) {
        camera = _camera;
    }

    public void setController(Controller _controller){
        controller = _controller;
    }

    public void move(float _deltaTime){
        double power = controller.get(GamePad.LEFT_ANALOG).valueDouble;
        double angle = controller.get(GamePad.LEFT_ROTATION).valueDouble;
        float x = camera.position.x + (float)(Math.sin(Math.toRadians(angle))*power);
        float y = camera.position.y + (float)(Math.cos(Math.toRadians(angle))*power);
        camera.position.set(x,y,camera.position.z);
    }


}
