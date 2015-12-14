package com.boomer.alphaassault.gameworld.units.skills;

import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 12/1/2015.
 */
public class Flashbang extends Skill {



    public static final int FLASHBANG_MAX = 2;
    public static final long COOLDOWN = 5  * 1000; //5 SECONDS



    public Flashbang(int _key)
    {
            super(_key);
            supply = FLASHBANG_MAX;
            targetType = Skill.TARGET_TYPE_POINT;
    }


    @Override
    public void resupply() {
            supply = FLASHBANG_MAX;
    }

    @Override
    public void use() {
        System.out.println("Flashbang");
            if(isReady()){
                if(supply>0){
                    //use
                }
            }
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
