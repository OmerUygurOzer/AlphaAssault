package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Water extends MapFeature {
    public static final int WATER_RADIUS = 10;

    public Water(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = false;
        blocksAerial = false;
        blocksDamage = false;
        radius = WATER_RADIUS;
        TextureRegion textureRegion = Resource.getTextureRegions(Resource.TEXTURE_REGION_OTHERS)[0][3];
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(20,20);
        ((BSprite)bDrawable).setPosition(_location.x,_location.y);
    }



}
