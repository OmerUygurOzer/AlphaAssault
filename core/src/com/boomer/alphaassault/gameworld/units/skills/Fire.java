package com.boomer.alphaassault.gameworld.units.skills;

import com.badlogic.gdx.math.Vector2;
import com.boomer.alphaassault.gameworld.projectiles.Bullet;
import com.boomer.alphaassault.gameworld.units.Unit;
import com.boomer.alphaassault.graphics.RenderState;
import com.boomer.alphaassault.resources.Resource;

/**
 * Created by Omer on 12/12/2015.
 */
public class Fire extends Skill {

    private static final float FIRE_COOLDOWN = 0.1f;

   public Fire(int _key){
        super(_key);
       targetType = Skill.TARGET_TYPE_ANGLE;
       icon = Resource.getTextureRegions(Resource.ICONS)[0][0];
       cooldown = Math.round(FIRE_COOLDOWN * 1000);
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
            Vector2 bulletPos = new Vector2(user.getCenter().x,user.getCenter().y);
            Bullet bullet = new Bullet(bulletPos, RenderState.DEPTH_SURFACE,world,user.getFacingAngle());
            bullet.setCollisionLimit(1);
            bullet.setDamage(10);
            bullet.setSource(user);
            bullet.setSpeed(0.1f);
            bullet.setRange(10);
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
