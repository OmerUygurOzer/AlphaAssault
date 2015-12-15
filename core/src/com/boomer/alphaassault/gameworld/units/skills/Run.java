package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.boomer.alphaassault.gameworld.gamelogic.buffs.AdjustedSpeed;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.resources.Resource;
import com.boomer.alphaassault.utilities.Location;

/**
 * Created by Omer on 12/1/2015.
 */
public class Run extends Skill{


    public static final long RUN_COOLDOWN = 12  * 1000; //12 SECONDS
    public static final long DURATION  = 5 * 1000;//5 SECONDS
    public static final int BOOST_PERCENTAGE = 100;

    public Run(int _key){
        super(_key);
        targetType = Skill.TARGET_TYPE_SELF;
        cooldown = RUN_COOLDOWN;
        icon = Resource.getTextureRegions(Resource.TEXTURE_REGION_SKILL_ICONS)[0][1];


    }


    @Override
    public void resupply() {

    }

    @Override
    public void use() {

    }

    @Override
    public void use(Unit _unit) {
        if(ready){
            ready = false;
            timer = System.currentTimeMillis();
            System.out.println("Run");
            AdjustedSpeed runBoost = new AdjustedSpeed(DURATION,AdjustedSpeed.PERCENTAGE,BOOST_PERCENTAGE);
            runBoost.inflict(user);
        }

    }

    @Override
    public void use(Location _target) {

    }

    @Override
    public void update() {
        if(System.currentTimeMillis() - timer > cooldown){
            ready = true;
        }
    }

}
