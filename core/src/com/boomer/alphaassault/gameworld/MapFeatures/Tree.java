package com.boomer.alphaassault.gameworld.mapfeatures;

import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 11/25/2015.
 */
public class Tree extends MapFeature {
    public Tree(Location _location) {
        super(_location);
        destroyable = false;
        blocksMovement = true;
        blocksBullets = true;
        blocksAerial = true;
        blocksDamage = true;
        radius = TREE_RADIUS;
    }


}
