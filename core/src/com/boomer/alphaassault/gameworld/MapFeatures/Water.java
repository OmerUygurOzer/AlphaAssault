package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;

import java.util.Random;

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
        size = radius * 2;
        Random random = new Random();
        int featureType = random.nextInt((3-1)+1)+1;
        image = Resource.getTextureRegions(Resource.WATER)[0][featureType];
        bDrawable = new BSprite(image);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(_center.x,_center.y);
    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }


}
