package com.boomer.alphaassault.gameworld.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.projectiles.Rocket;
import com.boomer.alphaassault.gameworld.skills.Skill;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/24/2015.
 */
public class Fire_Rocket extends Skill {

    private static final float ROCKET_CD = 0.2f;
    private static final int ROCKETS_MAX = 8;

    private static final float ROCKET_SPEED = 0.05f;
    private static final int ROCKET_DAMAGE = 20;
    private static final int ROCKET_COL_LIMIT = 1;
    private static final int ROCKET_RANGE = 18;

    public Fire_Rocket(Unit _user, int _key){
        super(_user,_key);

        cooldown = Math.round(ROCKET_CD * 1000);

        icon = Resource.getTextureRegions(Resource.ICONS)[0][7];

        supply = new Supply();
        supply.name  = "Rockets";
        supply.count = ROCKETS_MAX;
        supply.icon = icon;
        supply.COUNT_MAX = ROCKETS_MAX;
        targetType = Skill.TARGET_TYPE_ANGLE;
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
        if(ready){
            if(supply.count >0){
                ready = false;
                timer = System.currentTimeMillis();
                supply.count -= 1;
                Vector2 rLoc = new Vector2(user.getCenter().x,user.getCenter().y);
                float rAngle = (float)user.getFacingAngle();
                Rocket rocket = new Rocket(rLoc,RenderState.DEPTH_SURFACE,world,rAngle);
                rocket.setViewType(world.viewType);
                rocket.setDamage(ROCKET_DAMAGE);
                rocket.setSource(user);
                rocket.setSpeed(ROCKET_SPEED);
                rocket.setRange(ROCKET_RANGE);
                world.addEntity(rocket);

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
