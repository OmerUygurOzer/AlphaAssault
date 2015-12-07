package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Crate extends MapFeature {
    public Crate(Location _location) {
        super(_location);
        destroyable = true;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = false;
        blocksDamage = false;
        radius = CRATE_RADIUS;
        TextureRegion textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_OTHERS),0,0,32,32);
        featureSprite = new Sprite(textureRegion);
        featureSprite.setSize(20,20);
        featureSprite.setPosition(_location.x,_location.y);
    }
}
