package com.boomer.alphaassault.gameworld.mapfeatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

import java.util.Random;

/**
 * Created by Omer on 11/25/2015.
 */
public class Tree extends MapFeature {
    public static final int TREE_RADIUS = 10;

    public Tree(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = true;
        blocksDamage = true;
        radius = TREE_RADIUS;
        Random random = new Random();
        int treeType = random.nextInt((3-1)+1)+1;
        TextureRegion textureRegion = null;
        switch (treeType){
            case 1:
                textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_TREES),0,0,90,130);
                break;
            case 2:
                textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_TREES),90,0,90,130);
                break;
            case 3:
                textureRegion = new TextureRegion(Resource.getTexture(Resource.TEXTURE_TREES),180,0,90,130);
                break;
        }
        bDrawable = new BSprite(textureRegion);
        ((BSprite)bDrawable).setSize(20,40);
        ((BSprite)bDrawable).setPosition(_location.x,_location.y);
    }


}
