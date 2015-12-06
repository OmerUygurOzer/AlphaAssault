package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Water extends MapFeature {
    public Water(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = false;
        blocksAerial = false;
        blocksDamage = false;
        radius = WATER_RADIUS;
        TextureRegion textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_OTHERS),96,0,32,33);
        featureSprite = new Sprite(textureRegion);
        featureSprite.setSize(20,20);
        featureSprite.setPosition(_location.x,_location.y);
    }



}
