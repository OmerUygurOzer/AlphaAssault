package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Crate extends MapFeature {
    public static final int CRATE_RADIUS = 10;

    public Crate(Location _location) {
        super(_location);
        destroyable = true;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = false;
        blocksDamage = false;
        radius = CRATE_RADIUS;
        TextureRegion textureRegion = Resource.getTextureRegions(Resource.DOODADS)[0][0];
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(_location.x,_location.y);
    }
}
