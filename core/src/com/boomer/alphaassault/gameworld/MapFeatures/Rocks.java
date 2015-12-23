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
        size = radius * 2;
        Random random = new Random();
        int featureType = random.nextInt((3-1)+1)+1;
        image = Resource.getTextureRegions(Resource.ROCKS)[0][featureType];
        bDrawable = new BSprite(image);
        ((BSprite)bDrawable).setSize(16,16);
        ((BSprite)bDrawable).setPosition(center.x,center.y);
    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }
}
