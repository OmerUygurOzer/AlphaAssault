package com.boomer.alphaassault.gameworld.level.mapfeatures;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.elements.BSprite;

/**
 * Created by Omer on 1/3/2016.
 */
public class MapFeature extends MapFeatureBase {

    public MapFeature(Vector2 _center, GameWorld _world) {
        super(_center, _world);
    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }

    public void setDestroyable(boolean _d){
        destroyable = _d;
    }

    public void setBlocksMovement(boolean _bm){
        blocksMovement = _bm;
    }

    public void setBlocksBullets(boolean _bb){
        blocksBullets = _bb;
    }


   public void setBlocksAerial(boolean _ba){
       blocksAerial = _ba;
   }

    public void setBlocksDamage(boolean _bd){
        blocksDamage = _bd;
    }

    public void setDrawable(BSprite _bSprite){
        bDrawable = _bSprite;
    }

}
