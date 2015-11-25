package com.boomer.alphaassault.gameworld.MapFeatures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Water extends MapFeatureBase {
    public Water(Location _location) {
        super(FEATURE_TYPE_WATER, _location);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }
}
