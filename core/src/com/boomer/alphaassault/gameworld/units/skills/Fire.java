package com.boomer.alphaassault.gameworld.units.skills;

import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 12/12/2015.
 */
public class Fire extends Skill {

   public Fire(int _key){
        super(_key);
       targetType = Skill.TARGET_TYPE_POINT;
       icon = Resource.getTexture(Resource.TEXTURE_FIRE);
   }


    @Override
    public void resupply() {

    }

    @Override
    public void use() {
        System.out.println("Fire");
    }

    @Override
    public void use(Unit _unit) {

    }

    @Override
    public void use(Location _target) {

    }

    @Override
    public void update() {

    }


}
