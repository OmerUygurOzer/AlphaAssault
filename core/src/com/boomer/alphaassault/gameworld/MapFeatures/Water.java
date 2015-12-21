package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 11/25/2015.
 */
public class Water extends MapFeature {
    public static final int WATER_RADIUS = 10;

    public Water(Vector2 _center,GameWorld _world) {
        super(_center,_world);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = false;
        blocksAerial = false;
        blocksDamage = false;
        radius = WATER_RADIUS;
        TextureRegion textureRegion = Resource.getTextureRegions(Resource.DOODADS)[0][3];
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(_center.x,_center.y);
    }



}
