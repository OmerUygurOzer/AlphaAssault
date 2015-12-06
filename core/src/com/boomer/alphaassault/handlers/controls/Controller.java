package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.utilities.Location;
import sun.util.resources.cldr.lag.CalendarData_lag_TZ;

import java.util.HashMap;

/**
 * Created by Omer on 11/30/2015.
 */
public class Controller {

    public class Value {
        public double valueDouble;
        public boolean valueBoolean;
        public Location valueLocation;

        public Value(double _value) {
            valueBoolean = false;
            valueDouble = _value;
            valueLocation = null;
        }

        public Value(boolean _value) {
            valueBoolean = _value;
            valueDouble = 0;
            valueLocation = null;
        }

        public Value(Location _value) {
            valueBoolean = false;
            valueDouble = 0;
            valueLocation = new Location(_value);
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

    public void set(Integer _key, Location _value) {
        if(values.containsKey(_key)){
            values.get(_key).valueLocation.x = _value.x;
            values.get(_key).valueLocation.y = _value.y;

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