package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.projectiles.FlashBang;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/1/2015.
 */
public class Fire_Flashbang extends Skill {

    public static final int FLASHBANG_MAX = 2;
    private static final float FLASHBANG_CD = 5f;

    private static final float FLASHBANG_SPEED = 0.01f;
    private static final int FLASHBANG_RANGE = 10;
    private static final int FLASHBANG_DURATION  = 5;




    public Fire_Flashbang(int _key)
    {
            super(_key);
            cooldown = Math.round(FLASHBANG_CD * 1000);

            icon = Resource.getTextureRegions(Resource.ICONS)[0][2];

            supply = new Supply();
            supply.name  = "Flashbang";
            supply.count = FLASHBANG_MAX;
            supply.icon = icon;
            supply.COUNT_MAX = FLASHBANG_MAX;
            targetType = Skill.TARGET_TYPE_ANGLE;

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
        if(ready){
            if(supply.count >0){
                ready = false;
                timer = System.currentTimeMillis();
                supply.count -= 1;
                Vector2 fLoc = new Vector2(user.getCenter().x,user.getCenter().y);
                FlashBang flashBang = new FlashBang(fLoc, RenderState.DEPTH_SURFACE,world);
                flashBang.setViewType(world.viewType);
                flashBang.setDirectionAngle((float)user.getFacingAngle());
                flashBang.setSpeed(FLASHBANG_SPEED);
                flashBang.setRange(FLASHBANG_RANGE);
                flashBang.setDuration(FLASHBANG_DURATION);
                flashBang.setSource(user);

                world.addEntity(flashBang);

            }

        }
    }

    @Override
    public void update() {
        if(System.currentTimeMillis() - timer > cooldown){
            ready = true;
        }
    }

}
