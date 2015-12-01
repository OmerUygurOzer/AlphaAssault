package com.boomer.alphaassault.handlers.controls;

import com.boomer.alphaassault.utilities.Location;

import java.util.HashMap;

/**
 * Created by Omer on 11/30/2015.
 */
public class Controller {

    private class Value {
        public double valueDouble;
        public boolean valueBoolean;
        public Location valueLocation;

        public Value(double _value) {
            valueDouble = _value;
        }

        public Value(boolean _value) {
            valueBoolean = _value;
        }

        public Value(Location _value) {
            valueLocation = new Location(_value);
        }
    }


    HashMap<Integer, Double> doubles;
    HashMap<Integer, Boolean> booleans;
    HashMap<Integer, Location> locations;


    protected Controller() {
        doubles = new HashMap<Integer, Double>();
        booleans = new HashMap<Integer, Boolean>();
        locations = new HashMap<Integer, Location>();
    }


    public void set(Integer _key, double _value) {
        doubles.put(_key, _value);
    }

    public void set(Integer _key, boolean _value) {
        booleans.put(_key, _value);
    }

    public void set(Integer _key, Location _value) {
        locations.put(_key, _value);
    }

    public Value get(Integer _key) {
        if (doubles.containsKey(_key)) {
            return new Value(doubles.get(_key));
        }

        if (booleans.containsKey(_key)) {
            return new Value(booleans.get(_key));
        }

        if(locations.containsKey(_key)){
            return new Value(new Location(locations.get(_key)));
        }

        return null;
    }
}