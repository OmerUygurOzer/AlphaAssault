package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.projectiles.Bullet;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/12/2015.
 */
public class AssaultRifle extends Skill {

    private static final float ASSAULT_RIFLE_CD = 0.1f;

    private static final float BULLET_SPEED = 0.1f;
    private static final int BULLET_DAMAGE = 10;
    private static final int BULLET_COL_LIMIT = 1;
    private static final int BULLET_RANGE = 15;

   public AssaultRifle(int _key){
        super(_key);
       targetType = Skill.TARGET_TYPE_ANGLE;
       icon = Resource.getTextureRegions(Resource.ICONS)[0][0];
       cooldown = Math.round(ASSAULT_RIFLE_CD * 1000);
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
            ready = false;
            timer = System.currentTimeMillis();
            Bullet bullet = world.bulletPool.acquire();
            bullet.setCenter(user.getCenter().x,user.getCenter().y);
            bullet.setCollisionLimit(BULLET_COL_LIMIT);
            bullet.setDamage(BULLET_DAMAGE);
            bullet.setSource(user);
            bullet.setSpeed(BULLET_SPEED);
            bullet.setRange(BULLET_RANGE);
            bullet.setDirectionAngle((float)user.getFacingAngle());
            bullet.setViewType(world.viewType);
            world.addEntity(bullet);
        }

    }

    @Override
    public void update() {
        if(System.currentTimeMillis() - timer > cooldown){
            ready = true;
        }
    }


}
