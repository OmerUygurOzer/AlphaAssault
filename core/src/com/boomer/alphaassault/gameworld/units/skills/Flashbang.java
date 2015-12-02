package com.boomer.alphaassault.gameworld.units.skills;

/**
 * Created by Omer on 12/1/2015.
 */
public class Flashbang extends Skill {

    public static final int FLASHBANG_MAX = 2;
    public static final long COOLDOWN = 5  * 1000; //5 SECONDS



    public Flashbang(){
            supply = FLASHBANG_MAX;
    }


    @Override
    public void resupply() {
            supply = FLASHBANG_MAX;
    }

    @Override
    public void use() {
            if(isReady()){
                if(supply>0){
                    //use
                }
            }
    }

    @Override
    public void update() {

    }
}
