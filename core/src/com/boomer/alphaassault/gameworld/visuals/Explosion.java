package com.boomer.alphaassault.gameworld.visuals;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/24/2015.
 */
public class Explosion extends Visual {
    private static final int DEFAULT_SIZE = 50;
    private static final int MAX_FRAMES_INDEX = 80;

    private int size;



    public Explosion(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
        effect = new BAnimation(Resource.getTextureRegions(Resource.EXPLOSION), BAnimation.Type.STILL);
        size = DEFAULT_SIZE;
        ((BAnimation)effect).setSize(size,size);
        ((BAnimation) effect).setCenter(center.x,center.y);
        ((BAnimation) effect).setSecondsPerFrame(2f/81f);
    }



    @Override
    public void uponCollision(Entity _entity) {

    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }

    @Override
    public void update(float _deltaTime) {
        ((BAnimation)effect).update(_deltaTime);
        if(((BAnimation)effect).getCurrentFrame() == MAX_FRAMES_INDEX){markRemoved();return;}
        RenderStateManager.updatingStatePointer.updateElement(referenceId,depth,effect);
    }
}
