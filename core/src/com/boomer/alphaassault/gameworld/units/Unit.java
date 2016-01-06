package com.boomer.alphaassault.gameworld.units;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;

/**
 * Created by Omer on 1/5/2016.
 */
public class Unit extends UnitBase {



    public Unit(int _team, Vector2 _center, GameWorld _world) {
        super(_team, _center, _world);
    }

    @Override
    public void resupply() {

    }

    @Override
    public void move(float _deltaTime, float _x, float _y, double _angle) {

    }

    @Override
    public void uponCollision(Entity _entity) {

    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }
}
