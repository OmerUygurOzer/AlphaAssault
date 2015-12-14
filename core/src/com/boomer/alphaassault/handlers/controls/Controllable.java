package com.boomer.alphaassault.handlers.controls;



/**
 * Created by Omer on 12/11/2015.
 */
public interface Controllable {
    public void setAnalog(Controller _controller);
    public void setConsole(Controller _controller);
    public void control(float _deltaTime);
}
