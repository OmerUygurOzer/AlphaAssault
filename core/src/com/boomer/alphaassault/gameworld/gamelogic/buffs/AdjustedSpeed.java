package com.boomer.alphaassault.gameworld.gamelogic.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.Renderable;
import com.boomer.alphaassault.graphics.elements.BSprite;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/12/2015.
 */
public class AdjustedSpeed extends Buff {

    public static final int PERCENTAGE = 0;
    public static final int ABSOLUTE = 1;

    private int adjustment;
    private int finalAdjustment;

    private int type;

    public AdjustedSpeed(long _duration,int _type,int _adjustment) {
        super(_duration);
        type = _type;
        adjustment = _adjustment;
        icon = new BSprite(Resource.getTextureRegions(Resource.TEXTURE_REGION_SKILL_ICONS)[0][1]);
        icon.setSize(Buff.WIDTH,Buff.HEIGHT);
    }



    @Override
    public void inflict(Unit _unit) {
        super.inflict(_unit);
        switch (type){
            case PERCENTAGE:
                finalAdjustment = _unit.getBaseMovementSpeed()*adjustment/100;
                break;
            case ABSOLUTE:
                finalAdjustment = adjustment;
                break;
            default:
                //DO NOTHING
                break;

        }
        _unit.adjustMovementSpeed(finalAdjustment);
        System.out.println(_unit.getMovementSpeed());
    }

    @Override
    public void deflict(Unit _unit) {
        super.deflict(_unit);
        _unit.adjustMovementSpeed(-finalAdjustment);
        System.out.println(_unit.getMovementSpeed());
    }


}
