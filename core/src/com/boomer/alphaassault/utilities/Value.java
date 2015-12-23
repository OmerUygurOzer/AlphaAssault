package com.boomer.alphaassault.utilities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Omer on 12/22/2015.
 */
public class Value {

    public double valueDouble;
    public boolean valueBoolean;
    public Vector2 valueVector2;

    public Value(double _value) {
        valueBoolean = false;
        valueDouble = _value;
        valueVector2 = null;
    }

    public Value(boolean _value) {
        valueBoolean = _value;
        valueDouble = 0;
        valueVector2 = null;
    }

    public Value(Vector2 _value) {
        valueBoolean = false;
        valueDouble = 0;
        valueVector2 = new Vector2(_value);
    }
}
