package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Bush extends MapFeature {
    public static final int BUSH_RADIUS = 8;

    public Bush(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = false;
        blocksBullets = false;
        blocksAerial = false;
        blocksDamage = false;
        radius = BUSH_RADIUS;
        TextureRegion textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_OTHERS),64,0,32,32);
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(20,20);
        ((BSprite)bDrawable).setPosition(_location.x,_location.y);
    }


}
