package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 11/25/2015.
 */
public class Crate extends MapFeature {
    public static final int CRATE_RADIUS = 10;

    public Crate(Vector2 _center,GameWorld _world) {
        super(_center,_world);
        destroyable = true;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = false;
        blocksDamage = false;
        radius = CRATE_RADIUS;
        TextureRegion textureRegion = Resource.getTextureRegions(Resource.DOODADS)[0][0];
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(_center.x,_center.y);
    }

    @Override
    public void receiveHit(int _hit, Unit _source) {
        markRemoved();
    }
}
