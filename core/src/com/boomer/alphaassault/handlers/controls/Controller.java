package com.boomer.alphaassault.handlers.controls;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

/**
 * Created by Omer on 11/30/2015.
 */
public class Controller {

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


    protected HashMap<Integer, Value> values;

    protected Controller() {
       values = new HashMap<Integer, Value>();
    }


    public void set(Integer _key, double _value) {
        if(values.containsKey(_key)){
            values.get(_key).valueDouble = _value;
        }else{
            values.put(_key,new Value(_value));
        }
    }

    public void set(Integer _key, boolean _value) {
        if(values.containsKey(_key)){
            values.get(_key).valueBoolean = _value;
        }else{
            values.put(_key,new Value(_value));
        }
    }

    public void set(Integer _key, Vector2 _value) {
        if(values.containsKey(_key)){
            values.get(_key).valueVector2.x = _value.x;
            values.get(_key).valueVector2.y = _value.y;

        }else{
            values.put(_key,new Value(_value));
        }
    }

    public Value get(Integer _key) {
      if(values.containsKey(_key)){
          return values.get(_key);
      }
        return null;
    }

}