package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/1/2015.
 */
public class Flashbang extends Skill {

    public static final int FLASHBANG_MAX = 2;
    public static final long COOLDOWN = 5  * 1000; //5 SECONDS



    public Flashbang(int _key)
    {
            super(_key);


            icon = Resource.getTextureRegions(Resource.ICONS)[0][2];

            supply = new Supply();
            supply.name  = "Flashbang";
            supply.count = FLASHBANG_MAX;
            supply.icon = icon;
            supply.COUNT_MAX = FLASHBANG_MAX;
            targetType = Skill.TARGET_TYPE_POINT;

    }


    @Override
    public void resupply() {
            supply.count = FLASHBANG_MAX;
    }

    @Override
    public void use() {
            if(isReady()){
                if(supply.count >0){
                    //use
                }
            }
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
