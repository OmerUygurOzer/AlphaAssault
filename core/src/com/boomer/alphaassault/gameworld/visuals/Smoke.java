package com.boomer.alphaassault.gameworld.visuals;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.GameWorld;
import com.boomer.alphaassault.gameworld.gamelogic.Entity;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.elements.BAnimation;
import com.boomer.alphaassault.handlers.RenderStateManager;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/23/2015.
 */
public class Smoke extends Visual {
    private static final int DEFAULT_SIZE = 200;

    private int size;


    public Smoke(Vector2 _center, int _depth, GameWorld _world) {
        super(_center, _depth, _world);
        currentFrame = 0;
        totalFrames = Resource.getTextureRegions(Resource.SMOKE)[0].length;
        effect = new BAnimation(Resource.getTextureRegions(Resource.SMOKE), BAnimation.Type.STILL);
        size = DEFAULT_SIZE;
        ((BAnimation)effect).setSize(size,size);
        ((BAnimation) effect).setCenter(center.x,center.y);
        ((BAnimation) effect).setSecondsPerFrame(1f/14f);

    }

    @Override
    public void uponCollision(Entity _entity) {

    }

    @Override
    public void receiveHit(int _hit, Unit _source) {

    }


    @Override
    public void update(float _deltaTime) {
     if(System.currentTimeMillis() - initTime >= duration){
         markRemoved();
         return;
     }

        ((BAnimation)effect).update(_deltaTime);
        currentFrame = ((BAnimation) effect).getCurrentFrame();
        if(cycleUponFinish) {
            currentFrame = currentFrame == totalFrames ? 0 : currentFrame;
            ((BAnimation) effect).setCurrentFrame(currentFrame);
        }
        RenderStateManager.updatingStatePointer.updateElement(referenceId,depth,effect);
    }
}
