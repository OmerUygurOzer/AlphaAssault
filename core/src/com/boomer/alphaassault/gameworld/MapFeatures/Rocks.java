package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 11/25/2015.
 */
public class Rocks extends MapFeature {
    public static final int ROCKS_RADIUS = 8;

    public Rocks(Vector2 _center,GameWorld _world) {
        super(_center,_world);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = false;
        blocksDamage = true;
        radius = ROCKS_RADIUS;
        TextureRegion textureRegion = Resource.getTextureRegions(Resource.DOODADS)[0][1];
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(center.x,center.y);
    }
}
