package com.boomer.alphaassault.gameworld.particles;


import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;

/**
 * Created by Omer on 12/22/2015.
 */
public abstract class Particle extends Entity {

    protected Particle(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
    }
}
