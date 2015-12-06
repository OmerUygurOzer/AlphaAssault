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
        camera.position.set(camera.position.x+(float)controller.get(GamePad.LEFT_ANALOG).valueDouble,camera.position.y,camera.position.z);
    }


}
