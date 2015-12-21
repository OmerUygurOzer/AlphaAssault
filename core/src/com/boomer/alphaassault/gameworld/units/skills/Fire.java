package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/12/2015.
 */
public class Fire extends Skill {

   public Fire(int _key){
        super(_key);
       targetType = Skill.TARGET_TYPE_ANGLE;
       icon = Resource.getTextureRegions(Resource.TEXTURE_REGION_SKILL_ICONS)[0][0];
   }


    @Override
    public void resupply() {

    }

    @Override
    public void use() {

    }

    @Override
    public void use(Unit _unit) {

    }

    @Override
    public void use(Vector2 _target) {

    }

    @Override
    public void use(float _angle) {

    }

    @Override
    public void update() {

    }


}
