package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Rocks extends MapFeature {
    public Rocks(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = false;
        blocksDamage = true;
        radius = ROCKS_RADIUS;
        TextureRegion textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_OTHERS),32,0,32,32);
        featureSprite = new Sprite(textureRegion);
        featureSprite.setSize(20,20);
        featureSprite.setPosition(_location.x,_location.y);
    }
}
