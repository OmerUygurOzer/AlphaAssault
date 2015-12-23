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
public class Tree extends MapFeature {
    public static final int TREE_RADIUS = 8;

    public Tree(Vector2 _center,GameWorld _world) {
        super(_center,_world);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = true;
        blocksDamage = true;
        radius = TREE_RADIUS;
        size = radius * 2;
        Random random = new Random();
        int treeType = random.nextInt((2-1)+1)+1;
        image = Resource.getTextureRegions(Resource.TREES)[0][treeType];
        bDrawable = new BSprite(image);
        ((BSprite)bDrawable).setSize(16,32);
        ((BSprite)bDrawable).setPosition(center.x,center.y);
    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }


}
