package com.boomer.alphaassault.handlers.controls;



/**
 * Created by Omer on 12/11/2015.
 */
public interface Controllable {
    public void setAnalog(Analog _analog);
    public void setConsole(Console _console);
    public void control(float _deltaTime);
}
